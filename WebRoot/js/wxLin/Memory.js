 function randomData(i) {
		    value = value + Math.random() * 21 - 10;
		    return {
		        name: "data",
		        value: [i,Math.round(value)]
		    }
		}

		var data = [{name:"data",value:[0,5]},
		{name:"data",value:[1,5]},
		{name:"data",value:[2,5]},
		{name:"data",value:[3,5]},
		{name:"data",value:[4,5]},
		{name:"data",value:[5,5]},
		{name:"data",value:[6,5]},
		{name:"data",value:[7,5]},
		{name:"data",value:[8,5]},]
		var time = [1,2,3,4,5,6,7,8,9];
		var value = Math.random() * 100;
		for (var i = 0; i < 10; i++) {
		    data.push(randomData(i));
		}//初始化

		option = {
		    title: {
		        text: '内存'
		    },
		    xAxis: {
		        type: 'category',
		        splitLine: {
		            show: false
		        },
		        data :time
		    },
		    yAxis: {
		        type: 'value',
		        boundaryGap: [0, '100%'],
		        splitLine: {
		            show: false
		        }
		    },
		    series: [{
		        name: '模拟数据',
		        type: 'line',
		        showSymbol: false,
		        hoverAnimation: false,
		        data: data
		    }]
		};
		myChart.setOption(option);