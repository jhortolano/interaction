<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.jqplot.min.js"></script>
		<script type="text/javascript" src="plugins/jqplot.barRenderer.min.js"></script>
		<script type="text/javascript" src="plugins/jqplot.highlighter.min.js"></script>
		<script type="text/javascript" src="plugins/jqplot.cursor.min.js"></script>
		<script type="text/javascript" src="plugins/jqplot.pointLabels.min.js"></script>
		<link rel="stylesheet" type="text/css" href="js/jquery.jqplot.min.css" />

		<r:script>
		<!--<script class="code" type="text/javascript"> -->
		var plot1 = null;
		var s1 = ${stocksSet1};
	    var s2 = ${stocksSet2};
	    
	    
		$(document).ready(function () {


    plot1 = $.jqplot("chart2", [s2, s1], {
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
			                    show: true
			                },
			                renderer: $.jqplot.BarRenderer,
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
			            yaxis: {
			                tickOptions: {
			                    formatString: "$%'d"
			                },
			                rendererOptions: {
			                    forceTickAt0: true
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



					$('#timeLink').click(function() {
						$('#time').load(this.href); 
						var a1 = plot1.series[0].data;
						var a2 = plot1.series[1].data;
						plot1.series[0].data.push([2012,150000]);
						plot1.series[1].data.push([2012,150000]);
						plot1.replot();		
						return false;
						});

	
		</r:script>
		
		
<div id="chart2" style="width:700px; height:300px"></div>