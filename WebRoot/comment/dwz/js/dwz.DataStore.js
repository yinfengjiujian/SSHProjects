function DataStore(name,dataStore){
	this.S_initData();
	if (arguments.length == 2) {
		this.name = name;
		this._loadData(dataStore);					
	}else if (arguments.length == 1){
		var _temp = arguments[0];
		if(typeof _temp =="object") {						
			this._loadData(_temp);		
		}else{	
			this.name = _temp;	
		}
	}
	this.rowSet.setDataStore(this);
};
DataStore.prototype={
	//初始化必要数据
	S_initData : function(){
		this.name = "";
		this.pageNumber = 1;
		this.pageSize = 0;
		this.recordCount = 0;
		this.rowSet=new RowSet();
	},
	//加载数据
	_loadData : function(dataStore){
		if(dataStore instanceof Array){
			this.setRecordCount(dataStore.length);
			this.rowSet = new RowSet(dataStore);
			return;
		}
		if(dataStore.rowSet){
			this.rowSet = new RowSet(dataStore.rowSet);
			delete dataStore["rowSet"];
		}
	},
	setName: function(name) {
		this.name = name;
	},
	setPageSize: function(pageSize) {
		this.pageSize =pageSize;
	},
	
	setPageNumber: function(pageNo) {
		this.pageNumber = pageNo;
	},
	
	setRowSet : function(rowSet){
		this.rowSet = rowSet;
		this.rowSet.setDataStore(this);
		this.recordCount=this.rowSet.getTotalCount();
	},
	
	getName: function() {
		return this.name;
	},
	
	getPageSize: function() {
		return this.pageSize;
	},
	
	getPageNumber: function() {
		return this.pageNumber;
	},
	
	getRowSet: function() {
		return this.rowSet;
	},
	
	getRowSetName : function(){
		return this.rowSetName;
	},
	
	getParameter: function(name) {
		if(!this.parameters){this.parameters={}}
		var value = this.parameters[name];
		if (value != "undefined" ) {
			if (typeof value == "array" || value instanceof Array){
				return value[0];
			}
			return value;
		} 
	}, 
	
	addParameter:function(name, value) {	
		if(!this.parameters){this.parameters={}}
		if (typeof this.parameters[name] == "array" || this.parameters[name] instanceof Array) {
			this.parameters[name].push(value);
		}
		else{			
			this.parameters[name] = value;
		}
	},
	
	setParameter : function(name, value){	
		if(!this.parameters){this.parameters={}}
		this.parameters[name] = value;
	},
	
	removeParameter: function(name){
		delete this.parameters[name];
	}
};