$(function() {
	load();
	   $(document).on('keydown', function (event) {
	        // 键盘翻页事件
	        var e = event || window.event || arguments.callee.caller.arguments[0];
	        if (e && e.keyCode == 38 || e && e.keyCode == 37) {//上,左
	            // 上一页
	            $('.page-pre').click()
	        }
	        if (e && e.keyCode == 40 || e && e.keyCode == 39) {//下,右
	            // 下一页
	            $('.page-next').click()
	        }

	    })
});

function load() {
	var api = "${pageContext.request.contextPath}/imagesinfo/selectByPage";
	$('#exampleTable').bootstrapTable({
		url : api, // 请求的后台URL（*）
		method : 'post', // 请求方式：get/post（*）
		showRefresh : true, // 是否显示刷新按钮
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		showColumns : false, // 是否显示列操作按钮
		detailView : false, // 是否显示详细视图
		striped : true, // 设置为true会有隔行变色效果
		dataType : 'json', // 服务器返回的数据类型
		cache : false,
        pagination: true,  //是否显示分页（*）
        paginationLoop: true,
        paginationHAlign: 'right', //right, left
        paginationVAlign: 'bottom', //bottom, top, both
        paginationDetailHAlign: 'left', //right, left
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        pageSize : 10, // 如果设置了分页，每页数据条数
		pageNumber : 1, // 如果设置了分布，首页页码
		queryParamsType:'undefined',
		// 设置为limit则会发送符合RESTFull格式的参数
		singleSelect : true, // 设置为true将禁止多选
		clickToSelect : true, // 是否启用点击选中行
		dataField: 'msg',  //后端代码返回的数据格式对应列的key(msg.pkid)
		 contentType : "application/json",
		// 发送到服务器的数据编码类型
		pageList: [10, 25, 50, 100],   
		search : false, // 是否显示搜索框
		sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
		queryParams : function(params) {
			return {
				// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
				pageNo : params.pageNumber,
				pageSize : params.pageSize,
				//search : params.search,
			};
		},
		  //加载成功时执行
        onLoadSuccess: function(data){    
            console.log("加载成功");  
        },
        //加载失败时执行  
        onLoadError: function(status){  
            console.log("加载数据失败"+status);  
        },
		columns : [ {
			title : '序号',
			field : 'pkid',
			align : 'left',
			valign : 'center',
			width : '10%',
			formatter: function (value, row, index) {
	                return value;
	        }
		}, {
			title : '用户ID',
			field : 'Code',
			align : 'left',
			valign : 'center',
			width : '20%'

		}, /*{
			title : '用户姓名',
			field : 'Name',
			align : 'left',
			valign : 'center',
			width : '50%',
			cellStyle : function(value, row, index) {
				return {
					css : {
						"word-wrap" : "break-word",
						"word-break" : "normal"
					}
				};
			}

		}*/ ]
	});
}
