<template>
<div>
  <p>
    <button v-on:click="add()" class="btn btn-white btn-default btn-round">
      <i class="ace-icon fa fa-edit"></i>
      新增
    </button>

    <button class="btn btn-info btn-round" v-on:click="list(1)">
      刷新
      <i class="ace-icon fa fa-refresh"></i>
    </button>
  </p>
  <table id="simple-table" class="table  table-bordered table-hover">
    <thead>
    <tr>

      <th>ID</th>
      <th>名称</th>
      <th>课程ID</th>
      <th>操作</th>
    </tr>
    </thead>

    <tbody>
    <tr v-for="item in chapterList">

      <td>{{item.id}}</td>
      <td>{{item.name}}</td>
      <td>{{item.courseId}}</td>

      <td>
        <div class="hidden-sm hidden-xs btn-group">

          <button v-on:click="edit(item)" class="btn btn-xs btn-info">
            <i class="ace-icon fa fa-pencil bigger-120"></i>
          </button>

          <button v-on:click="del(item.id)" class="btn btn-xs btn-danger">
            <i class="ace-icon fa fa-trash-o bigger-120"></i>
          </button>

        </div>

        <div class="hidden-md hidden-lg">
          <div class="inline pos-rel">
            <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
              <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
            </button>

            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
              <li>
                <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
                                  <span class="blue">
                                    <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                  </span>
                </a>
              </li>

              <li>
                <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                  <span class="green">
                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                  </span>
                </a>
              </li>

              <li>
                <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                  <span class="red">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                  </span>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </td>
    </tr>

    </tbody>
  </table>

  <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <!--模态框的弹出和关闭，可以用js代码也可以用button属性：data-dismiss="css选择器"关闭；data-toggle="css选择器"打开-->
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">表单</h4>
        </div>
        <div class="modal-body">
          <form class="form-horizontal">
            <div class="form-group">
              <label class="col-sm-2 control-label">名称</label>
              <div class="col-sm-10">
                <input v-model="chapter.name" class="form-control" placeholder="名称">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">课程ID</label>
              <div class="col-sm-10">
                <input v-model="chapter.courseId" class="form-control" placeholder="课程ID">
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div>
  <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
</div>

</template>

<script>
  import Pagination from "../../components/pagination";
  export default {
    components: {Pagination},
    name: "chapter",
    //使用data定义组件内的变量，可用于做双向数据绑定，双向数据绑定是vue的核心功能之一
    data: function(){
      return {
        chapter: {},
        chapterList: []
      }
    },
    mounted: function() {
      let _this = this;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("business-chapter-sidebar");
    },
    methods: {
      save(){
        let _this = this;
        Loading.show();
        _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/save',_this.chapter).then((response)=>{
          Loading.hide();
          console.log("保存结果为:",response);
          let res = response.data;
          if (res.success){
            $('#form-modal').modal('hide');
            _this.list(1);
            toast.success("保存成功");
          }
        })
      },
      add(){
        let _this = this;
        _this.chapter = {};
        $('#form-modal').modal('show');
      },

      edit(item){
        let _this = this;
        _this.chapter = $.extend({},item);
        $('#form-modal').modal('show');
      },
      del(id){
        let _this = this;
        Swal.fire({
          title: '确认删除id为'+id+'这条数据嘛',
          text: '删除后不可恢复，确认删除?',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: '确认!'
        }).then((result)=>{
          if (result.value){
            _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/'+id).then((response)=>{
              Loading.show();
              let res = response.data;
              if (res.success){
                _this.list(1);
                toast.success("删除成功");
              }
            })
          }
        })
      },
      list(page){
        let _this = this;
        Loading.show();
        _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list',{
          page:page,
          size:_this.$refs.pagination.size
        }).then((response)=>{
          Loading.hide();
          console.log("查询的结果:",response);
          let res = response.data;
          _this.chapterList = res.content.list;
          _this.$refs.pagination.render(page, res.content.total);
        })
      }

    }
  }
</script>