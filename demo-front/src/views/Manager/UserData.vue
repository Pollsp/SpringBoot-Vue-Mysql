<template>
    <div>
      <div >
        <el-input style="width: 200px" placeholder="查询用户名" v-model="username"></el-input>
        <el-input style="width: 200px ;margin: 0 10px"  placeholder="查询姓名" v-model="name"></el-input>
        <el-button type="primary" @click="load(1)">查询</el-button>
        <el-button type="info" @click="reset">重置</el-button>
      </div>
      <div style="margin: 10px 0">
        <el-button type="primary" plain @click="handleAdd">新增</el-button>
        <el-button type="danger" plain @click="delBatch">批量删除</el-button>
      </div>
       <el-table :data="tableData" stripe @selection-change="handleSelectionChange">>

           <el-table-column type="selection" width="55" align="center">
           </el-table-column>
           <el-table-column label="序号" prop="id" ></el-table-column>
           <el-table-column label="用户名" prop="username" align="center"></el-table-column>
           <el-table-column label="姓名" prop="name"></el-table-column>
           <el-table-column label="手机号" prop="phone" align="center"></el-table-column>
           <el-table-column label="邮箱" prop="email"></el-table-column>
           <el-table-column label="地址" prop="address"></el-table-column>
           <el-table-column label="角色" prop="role"></el-table-column>
           <el-table-column label="操作" >

               <template slot-scope="scope">

             <div>
               <el-button type="primary" plain size="mini" @click="handleEdit(scope.row)">编辑</el-button>
             </div>
             <div style="display: flex;margin-top: 10px">
               <el-button type="danger" plain size="mini" @click="deleteData(scope.row)">删除</el-button>
             </div>
               </template>
           </el-table-column>






            </el-table>
      <div style="margin: 10px 0 ">
        <el-pagination
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[100, 200, 300, 400]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>



      </div>
      <el-dialog title="新增信息" :visible.sync="fromVisible" width="40%">
        <el-form :model="form" label-width="80px" style=" padding-right: 80px" :rules="rules" ref="formRef">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username" placeholder="用户名"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="form.name" placeholder="姓名" ></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input v-model="form.phone" placeholder="电话" ></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="form.email" placeholder="邮箱" ></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input type="textarea" v-model="form.address" placeholder="地址" ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="fromVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">保 存</el-button>
        </div>
      </el-dialog>















    </div>




</template>


<script>
// import request from '@/utils/request';






export default{
    name:"UserData",
    data(){
        return{
          total:0,
          username:'',
          name:'',
            tableData:[],//所以的数据
          pageNum: 1, //当前的页码
          pageSize: 5,//每页显示的个数
          fromVisible:false,
          form:{


          },
          user: JSON.parse(localStorage.getItem('honey-user')||'{}'),
          rules: {
            username:[{required:true, trigger: 'blur', message:'请输入用户名'}],
          },
          ids:[]
        }
    },created() {
      this.load()
  },
  methods:{
      load(pageNum){
        if (pageNum) this.pageNum=pageNum;

        this.$request.get('/user/selectByPage',{params:{
          pageNum: this.pageNum,
            pageSize: this.pageSize,
            username: this.username,
            name: this.name,
          }}).then(res =>{
          this.tableData= res.data.records
          this.total = res.data.total

        })
      },
    handleCurrentChange(pageNum){
        this.pageNum = pageNum
        this.load();
    },
    reset(){
        this.name=''
      this.username=''
      this.load();
    },
    save(){
      this.$refs['formRef'].validate((valid)=>{
        if (valid){

          this.$request({
            url: this.form.id ? '/user/updata' : '/user/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res=>{
            if (res.code ==='200'){
              this.$message.success("保存成功")
              this.load(1)
              this.fromVisible=false
            }else{
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
    handleAdd(){
        this.form={}
        this.fromVisible = true
    },
    handleEdit(row){
        this.form = JSON.parse(JSON.stringify(row))
        this.fromVisible = true
    },
    deleteData(row){

      this.$confirm('确认删除吗？',"提示",{type:"warning"}).then(()=>{
        if (row.id === this.user.id){
          this.$message.warning("无法删除该信息！")
          return
        }
        this.$request.delete('/user/delete/'+row.id).then(res=>{
          if (res.code ==='200'){
            this.$message.success("删除成功")
            this.load(1)
          }else {
            this.$message.error(res.msg)
          }
        })
      }).catch(()=>{})

    },
    handleSelectionChange(rows){
    let ids = rows.map( v=> v.id) //将对象数组变成数字数组
    this.ids = ids


    },
    delBatch(){
        if (!this.ids.length){
          this.$message.warning("请选择数据！")
          return
        }

      this.$confirm('确认删除选中数据吗？',"提示",{type:"warning"}).then(()=>{
        for (let x=0;x<=this.ids.length;x++){
          if (this.user.id === this.ids[x]){
            this.$message.warning("不能删除正在使用的账号的信息！")
            return;
          }
        }
        this.$request.delete('/user/delete/batch',{
          data: this.ids
        }).then(res=>{
          if (res.code ==='200'){
            this.$message.success("删除成功")
            this.load(1)
          }else {
            this.$message.error(res.msg)
          }
        })
      }).catch(()=>{})
    }


    }




}





</script>
<style scoped>

</style>