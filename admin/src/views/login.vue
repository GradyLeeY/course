<template>
  <div class="main-container">
    <div class="main-content">
      <div class="row">
        <div class="col-sm-10 col-sm-offset-1">
          <div class="login-container">
            <div class="center">
              <h1>
                <i class="ace-icon fa fa-leaf green"></i>
                <span class="red">Ace</span>
                <span class="white" id="id-text2">控台管理</span>
              </h1>
              <h4 class="blue" id="id-company-text">&copy; Company Grady</h4>
            </div>

            <div class="space-6"></div>

            <div class="position-relative">
              <div id="login-box" class="login-box visible widget-box no-border">
                <div class="widget-body">
                  <div class="widget-main">
                    <h4 class="header blue lighter bigger">
                      <i class="ace-icon fa fa-coffee green"></i>
                      Please Enter Your Information
                    </h4>

                    <div class="space-6"></div>

                    <form>
                      <fieldset>
                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input v-model="user.loginName" type="text" class="form-control" placeholder="请输入用户名"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                        </label>

                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input v-model="user.password" type="password" class="form-control" placeholder="请输入密码"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                        </label>

                        <div class="space"></div>

                        <div class="clearfix">
                          <label class="inline">
                            <input type="checkbox" class="ace"/>
                            <span class="lbl"> Remember Me</span>
                          </label>

                          <button type="button" class="width-35 pull-right btn btn-sm btn-primary" v-on:click="login()">
                            <i class="ace-icon fa fa-key"></i>
                            <span class="bigger-110">Login</span>
                          </button>
                        </div>

                        <div class="space-4"></div>
                      </fieldset>
                    </form>

                    <div class="social-or-login center">

                    </div>

                    <div class="space-6"></div>

                  </div><!-- /.widget-main -->

                  <div class="toolbar clearfix">
                    <div>
                    </div>

                    <div>

                    </div>
                  </div>
                </div><!-- /.widget-body -->
              </div><!-- /.login-box -->

            </div><!-- /.position-relative -->

          </div>
        </div><!-- /.col -->
      </div><!-- /.row -->
    </div><!-- /.main-content -->
  </div><!-- /.main-container -->
</template>

<script>

  export default {
    name: 'login',
    data: function(){
      return{
        user: {}
      }
    },
    mounted: function () {
        $('body').removeClass('no-skin');
        $('body').attr('class', 'login-layout light-login');
    },
    methods: {
        login(){
          let _this = this;
          _this.user.password = hex_md5(_this.user.password+KEY);
          _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/login',_this.user).then((response)=>{
            let resp = response.data;
            if (resp.success){
              Tool.setLoginUser(resp.content);
              _this.$router.push("/welcome");
            }else {
              Toast.warning(resp.message);
            }
          });
        }
    }
  }
</script>