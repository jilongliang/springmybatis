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
</head>
<body data-ng-app="app" data-ng-controller="AppCtrl">
	<div data-ui-view></div>
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
	<script src="<%=basePath %>js//config-router.js"></script> 
</body>
</html>