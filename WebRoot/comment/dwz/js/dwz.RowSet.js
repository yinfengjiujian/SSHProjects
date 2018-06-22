function RowSet(data){
	Buffer = {
        PRIMARY: 0,
        FILTER: 1,
        DELETE: 2
    };
    
	this["primary"] = [];
	this["delete"] = [];
	this["filter"] = [];
	this.initialCount=0;
	if(data){
		if(typeof data.length == "number"){
			this["primary"] = data;
			this.initialCount=data.length;
		}else {
			if(typeof data["primary"] != "undefined"){
				this["primary"] = data["primary"];
				this.initialCount+=data["primary"].length;
			}
			if(typeof data["delete"] != "undefined"){
				this["delete"] = data["delete"] ;
			}
			if(typeof data["filter"]!= "undefined"){
				this["filter"] = data["filter"];
			}
		}
	}
};

RowSet.prototype={
	onItemChanging: function(thisRow, itemName, value, index) {
		//
		//console.info("void onItemChanging");
	},
	
	onItemChanged: function (thisRow, itemName, value, index) {
		//
		//console.info("void onItemChanged");
	},
	setDataStore: function(dataStore){
		this._dataStore = dataStore;
	},
	
	getTotalCount: function(){
		return (this["primary"].length + this["filter"].length + this["delete"].length);
	},
	
	getRow: function(rowIndex, bufferName){
		var buff = this._getBuff(bufferName);
		if(!buff[rowIndex]){
			return null;
		}
		return new Row(this,buff[rowIndex], rowIndex);
	},
	
	_getBuff: function(bufferName){
		return (bufferName == Buffer.DELETE ? this["delete"] : (bufferName == Buffer.FILTER?this["filter"]:this["primary"]));
	},
};