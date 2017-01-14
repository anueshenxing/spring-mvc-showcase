<%--
  Created by IntelliJ IDEA.
  User: 张晓磊
  Date: 2017/1/13
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script type="text/javascript" src="<c:url value="/resources/echarts/echarts-all.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/jquery/1.8.2/jquery-1.8.2.min.js" />"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 1000px;height:600px;"></div>
<script type="text/javascript" language="javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    option = {
        title : {
            text: 'webkit内核依赖',
            subtext: '数据来自网络',
            x:'right',
            y:'bottom'
        },
        tooltip : {
            trigger: 'item',
            formatter : "{b}"
        },
        toolbox: {
            show : true,
            feature : {
                restore : {show: true},
                magicType: {
                    show: true,
                    type: ['force', 'chord'],
                    option: {
                        chord: {
                            minRadius : 2,
                            maxRadius : 10,
                            ribbonType: false,
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true,
                                        rotate: true
                                    },
                                    chordStyle: {
                                        opacity: 0.2
                                    }
                                }
                            }
                        },
                        force: {
                            minRadius : 5,
                            maxRadius : 8,
                            itemStyle : {
                                normal : {
                                    label: {
                                        show: false
                                    },
                                    linkStyle : {
                                        opacity : 0.5
                                    }
                                }
                            }
                        }
                    }
                },
                saveAsImage : {show: true}
            }
        },
        legend : {
            data : ['HTMLElement', 'WebGL', 'SVG', 'CSS', 'Other'],
            orient : 'vertical',
            x : 'left'
        },
        noDataEffect: 'none',
        series :[{
            type: 'force'
        }]
    };
    myChart.setOption(option);
    myChart.hideLoading();
    $.ajax({
        url: 'get_webkit_dep',
        type: "GET",
        dataType: 'json',
        async: true,

        success: function (data) {
            option.series[0] = {
                type: 'force',
                name: 'webkit-dep',
                itemStyle: {
                    normal : {
                        linkStyle : {
                            opacity : 0.5
                        }
                    }
                },
                categories: data.categories,
                nodes: data.nodes,
                links: data.links,
                minRadius: 5,
                maxRadius: 8,
                gravity: 1.1,
                scaling: 1.1,
                steps: 20,
                large: true,
                useWorker: true,
                coolDown: 0.995,
                ribbonType: false
            };

            myChart.setOption(option);
            myChart.hideLoading();
        },
        error:function(){
            alert("error")
        }

    });
</script>
</body>
</html>
