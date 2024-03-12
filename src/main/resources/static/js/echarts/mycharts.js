function weightChart(month, dayData, weightData){
  var myChart = echarts.init(
  document.getElementById("charts"))
  var option={
    title:{text:`${month}月 体重推移`},
    xAxis:{
      type:"category",
      data:dayData.split(",")
    },
    yAxis:{
      type:"value",
      name:"体重kg",
      min:0
    },
    series: [{
      data:weightData.split(","),
      type: "line"
    },
    {
      data:weightData.split(","),
      type: "bar"
    }]
  }//グラフの設定
  myChart.setOption(option)
}