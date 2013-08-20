
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
		<div id="list-stockChart" class="content scaffold-list" role="main">
			<h1>IBEX 35</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			Current Price: 
			<div id="lastPrice"/>
			<g:if test="${stockQuoteRed==true}">
			<p style="color:red">
			${stockCurrentValue}
			</p>
			</g:if>
			<g:else>
			<p style="color:green">
			${stockCurrentValue}
			</p>
			</g:else>
			
		</div>

		<div id="chart1" style="width:500px; height:400px"></div>
		
		
		<script type="text/javascript">

		var plot1 = null;
		var s1 = ${stocksSet1};
	    var s2 = ${stocksSet2};
	    
	    
		$(document).ready(function () {


    plot1 = $.jqplot("chart1", [s2], {
			        // Turns on animatino for all series in this plot.
			        //animate: true,
			        // Will animate plot on calls to plot1.replot({resetAxes:true})
			        animateReplot: true,
			        cursor: {
			            show: true,
			            zoom: true,
			            looseZoom: true,
			            showTooltip: false
			        },
			        series:[
			            {
			                pointLabels: {
			                    show: false
			                },
			                renderer: $.jqplot.CanvasAxisLabelRenderer,
			                showHighlight: false,
			                yaxis: 'y2axis',
			                rendererOptions: {
			                    // Speed up the animation a little bit.
			                    // This is a number of milliseconds. 
			                    // Default for bar series is 3000. 
			                    animation: {
			                        speed: 2500
			                    },
			                    barWidth: 15,
			                    barPadding: -15,
			                    barMargin: 0,
			                    highlightMouseOver: false
			                }
			            },
			            {
			                rendererOptions: {
			                    // speed up the animation a little bit.
			                    // This is a number of milliseconds.
			                    // Default for a line series is 2500.
			                    animation: {
			                        speed: 2000
			                    }
			                }
			            }
			        ],
			        axesDefaults: {
			            pad: 0
			        },
			        axes: {
			            // These options will set up the x axis like a category axis.
			            xaxis: {
			                tickInterval: 1,
			                drawMajorGridlines: false,
			                drawMinorGridlines: true,
			                drawMajorTickMarks: false,
			                rendererOptions: {
			                tickInset: 0.5,
			                minorTicks: 1
			            }
			            },
			            
			            y2axis: {
			                tickOptions: {
			                    formatString: "$%'d"
			                },
			                rendererOptions: {
			                    // align the ticks on the y2 axis with the y axis.
			                    alignTicks: true,
			                    forceTickAt0: true
			                }
			            }
				        },
				        highlighter: {
				            show: true,
				            showLabel: true,
				            tooltipAxes: 'y',
				            sizeAdjust: 7.5 , tooltipLocation : 'ne'
				        }
				    });
				   
				});



			function refresh(){
				$.get("stockChart/getFullData", function(data, textStatus)
				        {
			        		var dataParsed = JSON.parse(data);
			        		var minMax = dataParsed[0];
			        		dataParsed.shift();
							plot1.series[0].data = dataParsed;
							var ddd = plot1.axes.y2axis.max;
							plot1.axes.y2axis.max = minMax[1];
							plot1.axes.y2axis.min = minMax[0];
							plot1.replot({resetAxes:['xaxis']});	
				        });	
				$.get("stockChart/getLastQuote", function(data, textStatus)
				        {
			        		var dataParsed = JSON.parse(data);
			        		$("#lastPrice").text(dataParsed);	
				        });	
			}

			refresh();
			window.setInterval(function(){
				refresh();
				}, 5000);
							

			
		</script>
		
		
	</body>
</html>
