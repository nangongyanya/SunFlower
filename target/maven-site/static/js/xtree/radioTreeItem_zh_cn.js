/*
 *	Sub class that adds a check box in front of the tree item icon
 *
 *	Created by Erik Arvidsson (http://webfx.eae.net/contact.html#erik)
 *
 *	Disclaimer:	This is not any official WebFX component. It was created due to
 *				demand and is just a quick and dirty implementation. If you are
 *				interested in this functionality the contact us
 *				http://webfx.eae.net/contact.html
 *
 *	Notice that you'll need to add a css rule the sets the size of the input box.
 *	Something like this will do fairly good in both Moz and IE
 *	
 *	input.tree-check-box {
 *		width:		auto;
 *		margin:		0;
 *		padding:	0;
 *		height:		14px;
 *		vertical-align:	middle;
 *	}
 *
 */

 var disableColor = webFXTreeConfig.disableColor;

function WebFXRadioTreeItem(sText, sValue, eParent, sIcon, sOpenIcon, bChecked, disabled) {
	this.base = WebFXTreeItem;
	this.base(sText, null, eParent, sIcon, sOpenIcon);	
	this._checked = bChecked;
	this._disabled = false;
	if(disabled) this._disabled = disabled;

    // luohc 2004-7-30.
	this.value = sValue==null?"":sValue;
	this.getRadio = function(){
		return getRadio(this.value);
	};
	if(bChecked != null && bChecked == true){
		_setCheckedObject(this.id);
	}
}

WebFXRadioTreeItem.prototype = new WebFXTreeItem;

WebFXRadioTreeItem.prototype.toString = function (nItem, nItemCount) {
	var foo = this.parentNode;
	var indent = '';
	if (nItem + 1 == nItemCount) { this.parentNode._last = true; }
	var i = 0;
	while (foo.parentNode) {
		foo = foo.parentNode;
		indent = "<img id=\"" + this.id + "-indent-" + i + "\" src=\"" + ((foo._last)?webFXTreeConfig.blankIcon:webFXTreeConfig.iIcon) + "\">" + indent;
		i++;
	}
	this._level = i;
	if (this.childNodes.length) { this.folder = 1; }
	else { this.open = false; }
	if ((this.folder) || (webFXTreeHandler.behavior != 'classic')) {
		if (!this.icon) { this.icon = webFXTreeConfig.folderIcon; }
		if (!this.openIcon) { this.openIcon = webFXTreeConfig.openFolderIcon; }
	}
	else if (!this.icon) { this.icon = webFXTreeConfig.fileIcon; }
	var label = "&nbsp;" + this.text.replace(/</g, '&lt;').replace(/>/g, '&gt;');

	var str = "<div id=\"" + this.id + "\" ondblclick=\"webFXTreeHandler.toggle(this);\" class=\"webfx-tree-item\" onkeydown=\"return webFXTreeHandler.keydown(this, event)\">";
	str += indent;
	str += "<img id=\"" + this.id + "-plus\" src=\"" + ((this.folder)?((this.open)?((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon):((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon)):((this.parentNode._last)?webFXTreeConfig.lIcon:webFXTreeConfig.tIcon)) + "\" onclick=\"webFXTreeHandler.toggle(this);\">"
	
	// insert radio
	var tempStr = "<input type=\"radio\"" + " class=\"tree-radio\"" +
		(this._checked ? " checked=\"checked\"" : "") +
		" onclick=\"webFXTreeHandler.all[this.parentNode.id].setChecked(this.checked)\"" +
		" value=\"" + this.value + "\" myId=\"" + this.id + "\" id=\"" + this.value 
		+ "-input\" " + (this._disabled?" disabled ": "") + " name=\"radioButton\">";
	str += tempStr;	
	// end insert checkbox
	
	//str += "<img id=\"" + this.id + "-icon\" class=\"webfx-tree-icon\" src=\"" + ((webFXTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"webFXTreeHandler.select(this);\"><a href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"webFXTreeHandler.focus(this);\" onblur=\"webFXTreeHandler.blur(this);\">" + label + "</a></div>";
	tempStr = "<img id=\"" + this.id + "-icon\" class=\"webfx-tree-icon\" src=\"" + ((webFXTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"webFXTreeHandler.select(this);\"><span  id=\"" + this.id + "-anchor\" onfocus=\"webFXTreeHandler.focus(this);\" onblur=\"webFXTreeHandler.blur(this);\" style=\"cursor:hand;color:" + this.getColor() + "\">" + label + "</span></div>";
	str += tempStr;
    // if(this._disabled) alert(tempStr);
	str += "<div id=\"" + this.id + "-cont\" class=\"webfx-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";
	for (var i = 0; i < this.childNodes.length; i++) {
		str += this.childNodes[i].toString(i,this.childNodes.length);
	}
	str += "</div>";
	this.plusIcon = ((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon);
	this.minusIcon = ((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon);
	return str;
}

WebFXRadioTreeItem.prototype.getChecked = function () {
	//var divEl = document.getElementById(this.id);
	//var inputEl = divEl.getElementsByTagName("INPUT")[0];
	var inputEl = getRadio();
	return this._checked = inputEl.checked;
};

WebFXRadioTreeItem.prototype.setChecked = function (bChecked) {
    // \u8fd9\u4e2a\u65b9\u6cd5\u88ab\u7f57\u6d2a\u81e3\u4fee\u6539 2004-7-30.
	/*
	if (bChecked != this.getChecked()) {
		var divEl = document.getElementById(this.id);
		var inputEl = divEl.getElementsByTagName("INPUT")[0];
		this._checked = inputEl.checked = bChecked;
		
		if (typeof this.onchange == "function")
			this.onchange();
	}*/
	if(getCheckObject() !=null && getCheckObject().id == this.id) return;

	this._checked = bChecked;	
    _setCheckedObject(this.id);
	//doCheck(this, bChecked);
	if (typeof this.onchange == "function"){
		this.onchange(this.text, this.value, bChecked);
	}else if(this.onchange != null && this.onchange != ""){
		var str = this.onchange + "('" + this.text + "','" + this.value + "'," + bChecked + ");";
		eval(str);
	}
};

/*****   \u4ee5\u4e0b\u662f\u9012\u5f52\u9009\u62e9CheckBox\u7684\u65b9\u6cd5   \u7f57\u6d2a\u81e3   2004-7-30   *******/
function getRadio(id){
	return document.getElementById(id + "-input");
}

var checkedObject  = null;

function _setCheckedObject(id){	
	var node = webFXTreeHandler.all[id];
    // var span = document.getElementById(radio.myId + "-anchor");
    // if(span) obj.text = span.innerText;
	var obj = {id: node.id, text: node.text, value: node.value, 
		toString: function(){
		   var str = "\nid = " + this.id + "\ntext = " + this.text + "\nvalue = " + this.value + "\n";
	       return str;
	    }
	};
	checkedObject = obj;
}

// \u83b7\u53d6\u9009\u4e2d\u7684\u8282\u70b9\u503c\u548c\u6587\u672c.
function getCheckObject(){
	return checkedObject;			
}

// \u83b7\u53d6\u6240\u6709\u7684\u9009\u4e2dradio\u7684\u503c. 
function getCheckValue(){	
	if(checkedObject != null){
		return checkedObject.value;
	}
	return null;
}

// \u8fd4\u56de\u9009\u4e2dradio\u7684\u6587\u672c\u3002
function getCheckText(){
	if(checkedObject != null){
		return checkedObject.text;
	}
	return null;
}

// \u8bbe\u7f6eRadio\u7684\u9009\u4e2d\u72b6\u6001.
function setRadioChecked(value, checked){
	var radio = document.getElementById(value + "-input");
	if(radio){
		radio.checked = checked;		
		_setCheckedObject(radio.myId);
	}
}

// \u8bbe\u7f6eRadio\u7684\u53ef\u7528\u72b6\u6001.
function disableRadio(value, disabled, recusive){
	var radio = document.getElementById(value + "-input");
	if(radio){
		radio.disabled = disabled;
        var id = radio.myId;
		var span = document.getElementById(id + "-anchor");
		if(span){
			if(disabled) span.style.color = disableColor;
			else span.style.color = "black";
		}
		if(recusive){
			var node = webFXTreeHandler.all[id];
			disabledChildren(node, disabled);		
		}
	}
}

function disabledChildren(node, disabled){
  for(var i=0; i<node.childNodes.length; i++){
	  var item = node.childNodes[i];
	  var id = item.id;
	  var radio = document.getElementById(item.value + "-input");
	  if(radio)radio.disabled  = disabled;	 
	  var span = document.getElementById(id + "-anchor");
	  if(span){
	    	if(disabled) span.style.color = disableColor;
		    	else span.style.color = "black";
	  }
	  disabledChildren(item, disabled);
  }
}

// \u9690\u85cfRadio.
function visiableRadio(visiable){
	if(visiable) disp = "";
	else disp = "none";
	var cbxs = document.getElementsByTagName("INPUT");
	if(cbxs && cbxs.length >0){
		for(var i=0; i<cbxs.length; i++){
			var cbx = cbxs[i];
			if(cbx.type.toUpperCase() == "RADIO"){
				cbx.style.display = disp;
			}
		}
	}
}

