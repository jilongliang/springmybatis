<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
 
 %>
    
    
<html lang="en">
<head>
<meta charset="utf-8">
<title>sm</title>
<meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> <meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">
<meta http-equiv="Pragma" content="no-cache">
<meta name="robots" content="noindex,nofollow">
<!-- Loading Bootstrap -->
<link href="plugins/flat-ui/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="plugins/css/animate.css" rel="stylesheet" type="text/css" />
<link href="plugins/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/css/simple-line-icons.css" rel="stylesheet" type="text/css" />
<link href="plugins/angular/toaster.css" rel="stylesheet" type="text/css" />
<!-- Loading Flat UI -->
<link href="plugins/flat-ui/css/flat-ui.css" rel="stylesheet" type="text/css" />
<!-- Loading APP -->
<link href="plugins/res/css/font.css" rel="stylesheet" type="text/css" />
<link href="plugins/res/css/app.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="plugins/res/img/favicon.jpg">
<style type="text/css">
.container {
	width: 970px !important;
}
</style>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
<!--[if lt IE 9]>
      <script src="plugins/flat-ui/vendor/html5shiv.js"></script>
      <script src="plugins/flat-ui/vendor/respond.min.js"></script>
    <![endif]-->
    
    
    <!-- JQUERY -->
	<script src="plugins/jquery/jquery.min.js"></script>
	<script src="plugins/jquery/jquery.validate.min.js"></script>
	<script src="plugins/jquery/additional-methods.min.js"></script>
	<!-- ANGULARJS -->
	<script src="plugins/angular/angular.min.js"></script>
	<script src="plugins/angular/ocLazyLoad.min.js"></script>
	<script src="plugins/angular/angular-animate.min.js"></script>
	<script src="plugins/angular/angular-sanitize.min.js"></script>
	<script src="plugins/angular/angular-resource.min.js"></script>
	<script src="plugins/angular/angular-ui-router.js"></script>
	<script src="plugins/angular/ngStorage.js"></script>
	<script src="plugins/angular/ui-load.js"></script>
	<script src="plugins/angular/ui-utils.min.js"></script>
	<script src="plugins/angular/paging.js"></script>
	<script type="text/javascript">var basePath='<%=basePath%>'</script>
	<!-- APP-->
	<script src="<%=basePath %>js/main.js"></script>
	<script src="<%=basePath %>js/config.js"></script>
	<script src="<%=basePath %>js/config-router.js"></script>
	<script src="<%=basePath %>js/userController.js"></script> 
</head>
<body data-ng-app="app">
	
	
	
	
<!-- 导航条 -->
<div class="bg-white bread-crumb">
    <ul class="breadcrumb b-a m-b-n-xs lter b-b wrapper-md">
        <li><a ui-href="main"><i class="fa fa-home"></i> 首页</a></li>
        <li class="active" ng-bind="title"></li>
    </ul>
</div>

<!-- 加载提醒 -->
<div ng-show="loading" class="text-center m-t-md text-lg"><i class="fa fa-spin fa-spinner"></i> 数据加载中...</div>

<!-- 内容区域 -->
<div ng-hide="loading" class="wrapper-md content">
    <!-- 数据显示 -->
    <div class="wrapper-data">
        <div class="panel panel-default">
            <!-- 头部 -->
            <div class="panel-heading clearfix">
                <a ui-sref="main.sys.user.create" class="btn btn-info pull-right">
                    <i class="icon-user-follow m-r-sm text-md"></i>添加账户</a>
                <div class="w-lg">
                    <form ng-submit="search()" class="ng-pristine ng-valid">
                        <div class="input-group search-box">
                            <input type="text" class="form-control" ng-model="param.keyword" placeholder="密码、用户名">
                            <i ng-show="user.keyword" ng-click="clearSearch()" class="fa fa-times-circle text-muted search-clear"></i>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                </div>
            </div>

            <!-- 数据表格 -->
            <div ng-show="pageInfo.size" class="table-responsive">
                <table class="table table-striped b-t b-light text-center">
                    <thead>
	                    <tr>
	                        <th>用户名</th>
	                        <th>密码</th>
	                        <th>年龄</th>
	                        <th>&nbsp;</th>
	                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="item in pageInfo.list"> 
                        <td class="v-middle">{{item.username}}</td>
                        <td class="v-middle">{{item.password}}</td>
                        <td class="v-middle">{{item.age}}</td>
                        <td class="v-middle">
                            <a ui-sref="main.sys.user.update({id: item.id})" class="btn btn-sm btn-default">
                                <i class="fa fa-edit m-r-xs text-sm"></i>编辑</a>
                            <button type="button" ng-show="item.enable==1"  ng-click="disableItem(item.id,0)" class="btn btn-sm btn-default m-l-xs">
                                <i class="fa fa-ban m-r-xs text-sm"></i>禁用</button>
                            <button type="button" ng-hide="item.enable==1"  ng-click="disableItem(item.id,1)" class="btn btn-sm btn-default m-l-xs">
                                <i class="fa fa-check m-r-xs text-sm"></i>启用</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div><!-- /.table-responsive -->

            <!-- 底部分页 -->
				<!-- 底部分页 -->
            <footer ng-show="pageInfo.size" class="panel-footer">
                <div class="row">
                    <div class="col-sm-6">
                        <small class="text-muted inline m-t-sm m-b-sm">当前显示{{pageInfo.size}}条 共{{pageInfo.total}}条</small>
                    </div>

                    <div ng-show="pageInfo.size" class="col-sm-6 text-right text-center-xs"
                         paging
                         hide-if-empty=true
                         ul-class="pagination pagination-sm m-t-none m-b-none"
                         page="pageInfo.pageNum"
                         page-size="pageInfo.pageSize"
                         total="pageInfo.total"
                         show-prev-next="true"
                         scroll-top="true"
                         paging-action="pagination(pageInfo.nextPage)">
                    </div>
                </div>
            </footer>

            <!-- 无数据提醒 -->
            <div ng-hide="pageInfo.size" class="m-md alert alert-warning text-center text-lg">暂无数据</div>
        </div><!-- /.panel -->
    </div><!-- /.wrapper-data -->

</div><!-- /.wrapper-md -->
	
</body>
</html>