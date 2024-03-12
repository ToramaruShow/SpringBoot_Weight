window.onload=()=>{
  var myChart = echarts.init(
  document.getElementById("charts"))
  var option={
    title:{text:'体重推移'},
    xAxis:{
      type:"category",
      data:[1,2,3,4]
    },
    yAxis:{
      type:"value",
      name:"体重kg",
      min:30
    },
    series: [{
      data: [10,25,45,67],
      type: "line"
    },
    {
      data: [10,25,45,67],
      type: "bar"
    }]
  }//グラフの設定
  myChart.setOption(option)
}