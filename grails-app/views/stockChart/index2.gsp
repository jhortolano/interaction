
<%@ page import="com.stocks.StockChart" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- <meta http-equiv="refresh" content="5" ><!--  Auto refresh each 5 seconds -->
		<meta name="layout" content="mobile">
		<g:set var="entityName" value="${message(code: 'stockChart.label', default: 'StockChart')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>


		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.jqplot.min.js"></script>
		<script type="text/javascript" src="plugins/jqplot.barRenderer.min.js"></script>
		<script type="text/javascript" src="plugins/jqplot.highlighter.min.js"></script>
		<script type="text/javascript" src="plugins/jqplot.cursor.min.js"></script>
		<script type="text/javascript" src="plugins/jqplot.pointLabels.min.js"></script>
		<link rel="stylesheet" type="text/css" href="js/jquery.jqplot.min.css" />

	</head>
	<body>
		<div data-role="header" data-position="fixed">
			<h1>IBEX 35</h1>
			<div data-role="navbar">
				<ul>
					<li><a data-icon="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				</ul>
			</div>
		</div>
		
		
	</body>
</html>
