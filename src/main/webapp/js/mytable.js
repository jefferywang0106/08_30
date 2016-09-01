function add_new_data() {

	var num = document.getElementById("mytable").rows.length;

	var Tr = document.getElementById("mytable").insertRow(num);

	Td = Tr.insertCell(Tr.cells.length);

	Td.innerHTML = '<input name="productName" type="text" size="16">';

	Td = Tr.insertCell(Tr.cells.length);
	Td.innerHTML = '<input name="price" type="text" size="16" onkeyup="return ValidateNumber(this,value)">';
	Td = Tr.insertCell(Tr.cells.length);
	Td.innerHTML = '<input type="button" class="btn btn-default" value="delete" required="" onclick="deleteRow(this)">';

}

function remove_data() {

	var num = document.getElementById("mytable").rows.length;

	if (num > 2) {

		document.getElementById("mytable").deleteRow(-1);
	}

}
function deleteRow(r) {
	var i = r.parentNode.parentNode.rowIndex;
	var num = document.getElementById("mytable").rows.length;
	if (num > 2)
		document.getElementById("mytable").deleteRow(i);
}
