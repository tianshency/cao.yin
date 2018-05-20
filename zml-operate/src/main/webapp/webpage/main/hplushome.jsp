<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--360浏览器优先以webkit内核解析-->


    <title>助民乐贷款后台</title>

    <link rel="shortcut icon" href="images/favicon.ico">
    <link href="plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="plug-in-ui/hplus/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="plug-in-ui/hplus/css/animate.css" rel="stylesheet">
    <link href="plug-in-ui/hplus/css/style.css?v=4.1.0" rel="stylesheet">


</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-4">

            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>待办任务</h5>
                </div>
                <div class="ibox-content">
                    <ol>
                        <li><a onclick="noApproveApply()">未审批申请任务(5)</a></li>
                        <li>审批中申请任务(2)</li>
                        <li>未审批额度审批(6)</li>
                        <li>审批中额度审批(2)</li>
                        <li><a onclick="noCreateContractList()">未创建合同(8)</a></li>
                        <li>未放款合同(1)</li>
                        <li>未审批放款(5)</li>
                        <li>未审批还款(5)</li>
                        <li>催收任务(1)</li>
                    </ol>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>版本升级日志</h5>
                </div>
                <div class="ibox-content no-padding">
                    <div class="panel-body">
                        <div class="panel-group" id="version">
                        <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v50">v3.6.6</a><code class="pull-right">2017.02.08</code>
                                    </h5>
                                </div>
                                <div id="v50" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="alert alert-warning">此版本微服务版本，支持插件开发，让我们共同期待后续版本的到来</div>
                                        <ol>
                                        	<li>插件：集成即时聊天webim聊天插件;</li>
                                            <li>插件：我的邮箱风格改版</li>
                                        	<li>online表单支持多配置表模式;</li>
                                            <li>online自定义样式BUG修正;</li>
                                            <li>online功能，word布局自定义模板功能优化：多文件上传、多选checkbox编辑赋值；</li>
                                            <li>ACE系统菜单支持自定义图标样式;</li>
                                            <li>ACE首页风格支持三级菜单;</li>
                                            <li>IE8兼容性修正;</li>
                                            <li>Ztree树标签;</li>
                                            <li>Sqlserver驱动升级，支持2008;</li>
                                            <li>系统管理，组织机构角色赋权功能实现；</li>
                                            <li>代码重构优化</li>
                                            <li>JEECG云插件下载地址：http://yun.jeecg.org </li>
                                            <li>更多插件发布，敬请期待。。</li>
                                        </ol>
                                    </div>
                                </div>
                        </div>
                        <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v41">v3.6.5</a><code class="pull-right">2016.07.18</code>
                                    </h5>
                                </div>
                                <div id="v41" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="alert alert-warning">此版本云应用插件开发版本，支持以插件方式升级平台功能，让我们共同期待后续版本的到来</div>
                                        <ol>
                                        	<li>Jeecg3.6.4稳定升级版，精简功能；</li>
                                        	<li>我的邮箱插件功能；</li>
                                            <li>用户锁定提示信息不准确修复；</li>
                                            <li>通过组织机构查看不到用户；</li>
                                            <li>Online代码生成器,支持自定义模板；</li>
                                            <li>Online代码生成器,单表生成支持java增强、sql增强、js增强、自定义按钮、自定义操作；</li>
                                            <li>删掉 OpenLayers-2.11、我的邮箱，在线文档的功能；</li>
                                            <li>多数据源，数据库密码进行加密存储；</li>
                                            <li>datagrid查询条件增加默认值属性；</li>
                                            <li>百度UE编辑器，上传问题解决；</li>
                                            <li>提供mysql、oracle11g、SqlServer2005脚本；</li>
                                            <li>插件发布：我的邮箱</li>
                                            <li>插件发布：CMS系统</li>
                                            <li>插件发布：微信企业号管理平台</li>
                                            <li>JEECG云插件下载地址：http://yun.jeecg.org </li>
                                            <li>更多插件发布，敬请期待。。</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                             <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v43">v3.6.3</a><code class="pull-right">2016.04.09</code>
                                    </h5>
                                </div>
                                <div id="v43" class="panel-collapse collapse">
                                    <div class="panel-body">
                                       <div class="alert alert-warning">此版本是一个扁平化UI风格版，提供4套风格供客户选择，让我们共同期待后续版本的到来</div>
                                        <ol>
                                            <li>ACE扁平化风格；</li>
                                            <li>代码生成器，支持restful后台代码生成；</li>
                                            <li>Online表单提供对外HTTP接口；</li>
                                            <li>用户，角色，组织机构，导入功能；</li>
                                            <li>多附件上传报错处理；</li>
                                            <li>查询过滤器查询报错处理；</li>
                                            <li>online代码生成器支持bootstrap表单风格生成；</li>
                                            <li>online代码生成器支持上传组件生成；</li>
                                            <li>升级minidao；</li>
                                            <li>在线文档管理；</li>
                                            <li>邮件管理；</li>
                                            <li>封装标签：用户标签，组织机构标签；</li>
                                            <li>移动报表展示；</li>
                                            <li>插件演示；</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v33">更多版本...</a><code class="pull-right"></code>
                                    </h5>
                                </div>
                                <div id="v33" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ol>
                                            <li>更多版本，请详见论坛：www.jeecg.org</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>服务器当前性能</h5>
                </div>
                <div class="ibox-content">
                    <p>服务器名称</p>
                    <ol>
                        <li>CPU:38%</li>
                        <li>内存:55%</li>
                        <li>网络:</li>
                        <li>……</li>
                    </ol>
                    <hr>
                    <div class="alert alert-warning">
                    	技术支持：<br>
                    	联系人：bobo<br>
                    	电话：13260111955
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="plug-in-ui/hplus/js/plugins/layer/layer.min.js"></script>
<script src="plug-in/tools/curdtools_zh-cn.js"></script>

<!-- 自定义js -->
<script src="plug-in-ui/hplus/js/content.js"></script>
</body>
<script type="text/javascript">
	function noApproveApply(){
	    var addurl = "zmlLoanApplicationController.do?noApproveList&";
	    createdetailwindow("未审批业务列表", addurl, 800, 400);
	}
	
	function noCreateContractList(){
	    var addurl = "zmlLoanContractController.do?noCreateContractList";
	    createdetailwindow("未创建合同列表", addurl, 800, 400);
	}
</script>
</html>
