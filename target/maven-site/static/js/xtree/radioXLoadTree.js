/*----------------------------------------------------------------------------\
|                               XLoadTree 1.11                                |
|-----------------------------------------------------------------------------|
|                         Created by Erik Arvidsson                           |
|                  (http://webfx.eae.net/contact.html#erik)                   |
|                      For WebFX (http://webfx.eae.net/)                      |
|-----------------------------------------------------------------------------|
| An extension to xTree that allows sub trees to be loaded at runtime by      |
| reading XML files from the server. Works with IE5+ and Mozilla 1.0+         |
|-----------------------------------------------------------------------------|
|                   Copyright (c) 1999 - 2002 Erik Arvidsson                  |
|-----------------------------------------------------------------------------|
| This software is provided "as is", without warranty of any kind, express or |
| implied, including  but not limited  to the warranties of  merchantability, |
| fitness for a particular purpose and noninfringement. In no event shall the |
| authors or  copyright  holders be  liable for any claim,  damages or  other |
| liability, whether  in an  action of  contract, tort  or otherwise, arising |
| from,  out of  or in  connection with  the software or  the  use  or  other |
| dealings in the software.                                                   |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| This  software is  available under the  three different licenses  mentioned |
| below.  To use this software you must chose, and qualify, for one of those. |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| The WebFX Non-Commercial License          http://webfx.eae.net/license.html |
| Permits  anyone the right to use the  software in a  non-commercial context |
| free of charge.                                                             |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| The WebFX Commercial license           http://webfx.eae.net/commercial.html |
| Permits the  license holder the right to use  the software in a  commercial |
| context. Such license must be specifically obtained, however it's valid for |
| any number of  implementations of the licensed software.                    |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| GPL - The GNU General Public License    http://www.gnu.org/licenses/gpl.txt |
| Permits anyone the right to use and modify the software without limitations |
| as long as proper  credits are given  and the original  and modified source |
| code are included. Requires  that the final product, software derivate from |
| the original  source or any  software  utilizing a GPL  component, such  as |
| this, is also licensed under the GPL license.                               |
|-----------------------------------------------------------------------------|
| 2001-09-27 | Original Version Posted.                                       |
| 2002-01-19 | Added some simple error handling and string templates for      |
|            | reporting the errors.                                          |
| 2002-01-28 | Fixed loading issues in IE50 and IE55 that made the tree load  |
|            | twice.                                                         |
| 2002-10-10 | (1.1) Added reload method that reloads the XML file from the   |
|            | server.                                                        |
/ 2003-05-06 | Added support for target attribute                             |
|-----------------------------------------------------------------------------|
| Dependencies: xtree.js - original xtree library                             |
|               xtree.css - simple css styling of xtree                       |
|               xmlextras.js - provides xml http objects and xml document     |
|                              objects                                        |
|-----------------------------------------------------------------------------|
| Created 2001-09-27 | All changes are in the log above. | Updated 2003-05-06 |
\----------------------------------------------------------------------------*/

/*
webFXTreeConfig.loadingText = "正在加载中...";
webFXTreeConfig.loadErrorTextTemplate = "加载出错 \"%1%\"";
webFXTreeConfig.emptyErrorTextTemplate = "";//"提示信息 \"%1%\" 没有包含任何 tree 条目";
*/

/*
 * WebFXRadioLoadTree class
 */

function WebFXRadioLoadTree(sText, sXmlSrc, sAction, sBehavior, sIcon, sOpenIcon) {
	// call super
	this.WebFXTree = WebFXTree;
	this.WebFXTree(sText, sAction, sBehavior, sIcon, sOpenIcon);

	// setup default property values
	this.src = sXmlSrc;
	this.loading = false;
	this.loaded = false;
	this.errorText = "";

	// check start state and load if open
	if (this.open)
		radio_startLoadXmlTree(this.src, this);
	else {
		// and create loading item if not
		this._loadingItem = new WebFXTreeItem(webFXTreeConfig.loadingText);
		this.add(this._loadingItem);
	}
}

WebFXRadioLoadTree.prototype = new WebFXTree;

// override the expand method to load the xml file
WebFXRadioLoadTree.prototype._webfxtree_expand = WebFXTree.prototype.expand;
WebFXRadioLoadTree.prototype.expand = function() {
	if (!this.loaded && !this.loading) {
		// load
		radio_startLoadXmlTree(this.src, this);
	}
	this._webfxtree_expand();
};


/*
 * WebFXLoadRadioTreeItem class
 */

function WebFXLoadRadioTreeItem(sText, sValue, sXmlSrc, eParent, sIcon, sOpenIcon, bChecked, disabled) {
	// call super	
	//this.WebFXTreeItem = WebFXTreeItem;
	//this.WebFXTreeItem(sText, sAction, eParent, sIcon, sOpenIcon);

    //this.WebFXTreeItem = WebFXCheckBoxTreeItem;
	//this.WebFXTreeItem(sText, sValue, bChecked, eParent, sIcon, sOpenIcon);

	this.base = WebFXRadioTreeItem;
	this.base(sText, sValue, eParent, sIcon, sOpenIcon, bChecked, disabled);

	// setup default property values
	this.src = sXmlSrc;
	this.loading = false;
	this.loaded = false;
	this.errorText = "";

	// check start state and load if open
	if (this.open)
		radio_startLoadXmlTree(this.src, this);
	else {
		// and create loading item if not
		this._loadingItem = new WebFXTreeItem(webFXTreeConfig.loadingText);
		this.add(this._loadingItem);
	}

	 // luohc add. 2004.11.12
	this.loadChildren = function(){
		radio_startLoadXmlTree(this.src, this); 
	}
}

WebFXLoadRadioTreeItem.prototype = new WebFXRadioTreeItem;

// override the expand method to load the xml file
WebFXLoadRadioTreeItem.prototype._webfxtreeitem_expand = WebFXTreeItem.prototype.expand;
WebFXLoadRadioTreeItem.prototype.expand = function() {
	if (!this.loaded && !this.loading) {
		// load
		radio_startLoadXmlTree(this.src, this);
	}
	this._webfxtreeitem_expand();
};

// reloads the src file if already loaded
WebFXLoadTree.prototype.reload =
WebFXLoadRadioTreeItem.prototype.reload = function () {
	// if loading do nothing
	if (this.loaded) {
		var open = this.open;
		// remove
		while (this.childNodes.length > 0)
			this.childNodes[this.childNodes.length - 1].remove();

		this.loaded = false;

		this._loadingItem = new WebFXTreeItem(webFXTreeConfig.loadingText);
		this.add(this._loadingItem);

		if (open)
			this.expand();
	}
	else if (this.open && !this.loading)
		radio_startLoadXmlTree(this.src, this);
};

/*
 * Helper functions
 */

// creates the xmlhttp object and starts the load of the xml document
function radio_startLoadXmlTree(sSrc, jsNode) {
	if (jsNode.loading || jsNode.loaded)
		return;
	jsNode.loading = true;
	var xmlHttp = XmlHttp.create();	
	xmlHttp.open("GET", sSrc, true);	// async
	xmlHttp.onreadystatechange = function () {
		if (xmlHttp.readyState == 4) {
			radio_xmlFileLoaded(xmlHttp.responseXML, jsNode);
		}
	};
	xmlHttp.send(null);
	// call in new thread to allow ui to update
	/*window.setTimeout(function () {
		xmlHttp.send(null);
	}, 10);
	*/
}

function radio_createNode(oNode, parentDisabled) {	
	// retreive attributes
	var id = oNode.getAttribute("id");
	var text = oNode.getAttribute("text");
	var value = oNode.getAttribute("value");
	var checked = false;
	if(oNode.getAttribute("checked") == "true"){
       checked = true;
	}	
	var parent = null;
	var icon = oNode.getAttribute("icon");
	var openIcon = oNode.getAttribute("openIcon");
	var src = oNode.getAttribute("src");
	var target = oNode.getAttribute("target");
	var disabled = _getDisabled_B(oNode, parentDisabled);
	
	// create jsNode
	var jsNode;
	if (src != null && src != "")
		jsNode = new WebFXLoadRadioTreeItem(text, value, src, parent, icon, openIcon, checked, disabled);
	else
		jsNode = new WebFXRadioTreeItem(text,value, parent, icon, openIcon, checked, disabled);	
   
    // 将保存选中值.
    if(checked){
	    _setCheckedObject(jsNode.id); // 这里的id是系统自动生成的id，不是oid（业务对象id）。
    }
 
	// 调置公共属性。
    _setNodeCommonAttr(jsNode, oNode);

	// 设置扩展属性。
	_setNodeExtendedAttr(jsNode, oNode);

	return jsNode;
}

// Converts an xml tree to a js tree. See article about xml tree format
function radio_xmlTreeToJsTree(oNode, parentDisabled) {	
	// create jsNode
	var jsNode;  
	var type = oNode.getAttribute("type");
	if(type != null && type != ""){
		jsNode = _createNodeByType(type, oNode, parentDisabled);	
	}else{
		jsNode = radio_createNode(oNode, parentDisabled);
	}	

	// go through childNOdes
	var cs = oNode.childNodes;
	var l = cs.length;
	for (var i = 0; i < l; i++) {
		if (cs[i].tagName == "tree"){
			var disabled = _getDisabled_B(oNode, parentDisabled);
			jsNode.add( radio_xmlTreeToJsTree(cs[i], disabled), true );
		}
	}

	return jsNode;
}

function _getDisabled_B(oNode, parentDisabled){
	var disabled = false;
	if(oNode.getAttribute("disabled") == "true"){
	   disabled = true;
	}	
	if(parentDisabled == true) {disabled = true;}
	return disabled;
}

// Inserts an xml document as a subtree to the provided node
function radio_xmlFileLoaded(oXmlDoc, jsParentNode) {
	if (jsParentNode.loaded)
		return;

	var bIndent = false;
	var bAnyChildren = false;
	jsParentNode.loaded = true;
	jsParentNode.loading = false;

	// check that the load of the xml file went well
	if( oXmlDoc == null || oXmlDoc.documentElement == null) {
		//alert(oXmlDoc.xml + " kkks");
		jsParentNode.errorText = parseTemplateString(webFXTreeConfig.loadErrorTextTemplate,
							jsParentNode.text, jsParentNode.src);
	}
	else {
		// there is one extra level of tree elements
		var root = oXmlDoc.documentElement;

		// loop through all tree children
		var cs = root.childNodes;
		var l = cs.length;
		for (var i = 0; i < l; i++) {
			if (cs[i].tagName == "tree") {
				bAnyChildren = true;
				bIndent = true;
				//alert(jsParentNode._disabled);
				jsParentNode.add( radio_xmlTreeToJsTree(cs[i], jsParentNode._disabled), true);
			}
		}

		// if no children we got an error
		if (!bAnyChildren)
			jsParentNode.errorText = parseTemplateString(webFXTreeConfig.emptyErrorTextTemplate,
										jsParentNode.text, jsParentNode.src);
	}

	// remove dummy
	if (jsParentNode._loadingItem != null) {
		jsParentNode._loadingItem.remove();
		bIndent = true;
	}

	if (bIndent) {
		// indent now that all items are added
		jsParentNode.indent();
	}

	// show error in status bar
	if (jsParentNode.errorText != "")
		window.status = jsParentNode.errorText;
}

// parses a string and replaces %n% with argument nr n
/*
function parseTemplateString(sTemplate) {
	var args = arguments;
	var s = sTemplate;

	s = s.replace(/\%\%/g, "%");

	for (var i = 1; i < args.length; i++)
		s = s.replace( new RegExp("\%" + i + "\%", "g"), args[i] )

	return s;
}
*/