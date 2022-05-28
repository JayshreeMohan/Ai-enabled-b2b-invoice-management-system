var c = 0;

function makeAvailable(chkBox) {
    var boxes = document.getElementById('mark');
    if (chkBox.checked) {
        c++;
        document.getElementById('delete-btn').disabled = 0
        document.getElementById('edit-btn').disabled = false
        document.getElementById('delete-btn').style.border = '1px solid #14AFF1'
        document.getElementById('edit-btn').style.border = '1px solid #14AFF1'
        if (c >= 2) {
           // c = 0;
            document.getElementById('edit-btn').disabled = true
            document.getElementById('edit-btn').style.border = '1px solid #97A1A9'
        }
        // document.get
    } else {
        c--;
        var chk = false;
        let c1 = 0;
        for (let i = 0; i < mark.length; i++) { //counting the number of checked boxes
            if (mark[i].checked) {
                chk = true;
                c1++;
            }
        }
        if (chk == false) {
            document.getElementById('delete-btn').disabled = 1
            document.getElementById('edit-btn').disabled = true
            document.getElementById('delete-btn').style.border = '1px solid #97A1A9'
            document.getElementById('edit-btn').style.border = '1px solid #97A1A9'
        }
        if (c1 == 1) {
            document.getElementById('edit-btn').disabled = false
            document.getElementById('edit-btn').style.border = '1px solid #14AFF1'
        }
    }
}
//--------------------------------------------------- 137

/*****************************ADD MODAL***************************/
// Get the modal
var modal = document.getElementById("modaladd");
// Get the button that opens the modal
var btn = document.getElementById("add-btn");
// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) 
{
    if (event.target == modal) 
    {
        modal.style.display = "none";
    }
}

/*****************************EDIT MODAL***************************/

$('#edit-btn').click((event) => {
    $('table #mark:checked').each(function() {
        var rowvalue = $(this).closest('tr')
        let k = rowvalue.find('td:eq(1)').text();
        let r = rowvalue.find('td:eq(10)').text()
        let r1 = rowvalue.find('td:eq(15)').text();
        $('#sl').val(k)
        $('#invoice_currency').val(r)
        $('#cust_pymt_trms').val(r1)
        $('#modaledit').toggle('block')
    })
})

/*****************************DELETE MODAL***************************/
// Get the modal
var modaldelete = document.getElementById("modaldelete");
// Get the button that opens the modal
var deletebtn = document.getElementById("delete-btn");
// When the user clicks the button, open the modal 
deletebtn.onclick 
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) 
{
    if (event.target == modaldelete) {
        modaldelete.style.display = "none";
    }
}

/*************************ADVANCE SEARCH MODAL***********************/
// Get the modal
var modaladvancesearch = document.getElementById("modaladvancesearch");
// Get the button that opens the modal
var searchbtn = document.getElementById("advance-search-btn");
// When the user clicks the button, open the modal 
searchbtn.onclick = function() 
{
    modaladvancesearch.style.display = "block";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) 
{
    if (event.target == modaladvancesearch) {
        modaladvancesearch.style.display = "none";
    }
}

/***********************************************ADD BUTTON FUNCTIONALITY***********************************************/
$('#add_add_btn').click((event) => {
    let data = {
        business_code: $('#AddbusinessCode').val(),
        cust_number: $('#AddcustomerNumber').val(),
        clear_date: $('#AddclearDate').val(),
        buisness_year: $('#AddbusinessYear').val(),
        document_id: $('#AdddocumentId').val(),
        posting_date: $('#AddpostingDate').val(),
        document_create_date: $('#AdddocumentCreateDate').val(),
        due_date: $('#AdddueDate').val(),
        invoice_currency: $('#AddinvoiceCurrency').val(),
        document_type: $('#AdddocumentType').val(),
        posting_id: $('#AddpostingId').val(),
        total_open_amount: $('#AddtotalOpenAmount').val(),
        baseline_create_date: $('#AddbaselineCreateDate').val(),
        customer_payment_terms: $('#AddcustomerPaymentTerms').val(),
        invoice_id: $('#AddinvoiceId').val()
    }
    console.log(data);
    $.ajax({
        'url': 'http://localhost:8080/dummy/addInvoice',
        'type': 'POST',
        'data': data,
        'datatype': 'json'
    })
    .then(data => {
        $('table tbody').empty()
        $('#modaladd').toggle('none')
        getData(page)
    })
})

$('#add_cancel_btn').click(() => {
    $('#modaladd').toggle('none')
})

/***********************************************EDIT BUTTON FUNCTIONALITY***********************************************/

$('#edit_btn').click(() => {
    let data = {
        sl_no: $('#sl').val(),
        invoiceCurrency: $('#invoice_currency').val(),
        cust_payment_terms: $('#cust_pymt_trms').val()
    }
    console.log(data)
    $.ajax({
            'url': 'http://localhost:8080/dummy/editInvoice',
            'type': 'POST',
            'data': data,
            'datatype': 'json'
        })
        .then(data => {
            $('table tbody').empty()
            $('#modaledit').toggle('none')
            getData(page) //reloading the records
        })
})

$('#cancel_btn').click(() => {
    $('#modaledit').toggle('none')
})

/***********************************************DELETE BUTON FUNCTIONALITY***********************************************/
$('#saved').click(function(event) {

  let data = []
  $('table #mark:checked').each(function() {
      var rowvalue = $(this).closest('tr')
      let r = rowvalue.find('td:eq(1)').text()
      data.push(r)

  })
  console.log(JSON.stringify(data));
  data = JSON.stringify(data)

  $.ajax({
        'url': 'http://localhost:8080/dummy/deleteInvoice',
        'type': 'POST',
        'data': {sl_no : data},
        'datatype': 'json'
          
      })
      .then(res => {
          $('#delete-btn').prop('disabled', true);
          alert("deleted successfully")
          $('#modaldelete').toggle('none') //close the modal
          $('table tbody').empty()
          getData(page)
      })

})

//to handel the cancel event
$('#canceld').click(() => {
  $('#modaldelete').toggle('none')
})

/********************************************* ADVANCE BUTON FUNCTIONALITY **********************************************/

$('#advance_seach_search_btn').click(() => {
    let data = 
    {
        document_id: $('#AdvanceSearchDoumentId').val(),
        invoice_id: $('#AdvanceSearchInvoiceId').val(),
        customer_number: $('#AdvanceSearchCustomerNumber').val(),
        business_year: $('#AdvanceSearchBusinessYear').val()
    }
    console.log(data)
    $.ajax({
            'url': 'http://localhost:8080/dummy/advanceSearch',
            'type': 'GET',
            'data': data,
            'datatype': 'json'
        }).then(data => {
            $('table tbody').empty()
            $('#modaladvancesearch').toggle('none')
            data = JSON.parse(data);
            data = data.invoice;
            console.log(data);
            var tr = $('table tbody')
            data.map(item => {
                markup = `<tr id="trow">
                            <td class='row-value'><input type="checkbox" name="checkbox" class="td_checkbox"
                            onclick="makeAvailable(this)"></td>
                                    <td class="row-value">${item.sl_no}</td>
                                    <td class="row-value">${item.business_code}</td>
                                    <td class="row-value">${item.cust_number}</td>
                                    <td class="row-value">${item.clear_date}</td>
                                    <td class="row-value">${item.buisness_year}</td>
                                    <td class="row-value">${item.doc_id}</td>
                                    <td class="row-value">${item.posting_date}</td>
                                    <td class="row-value">${item.document_create_date}</td>
                                    <td class="row-value">${item.due_in_date}</td>
                                    <td class="row-value">${item.invoice_currency}</td>
                                    <td class="row-value">${item.document_type}</td>
                                    <td class="row-value">${item.posting_id}</td>
                                    <td class="row-value">${item.total_open_amount}</td>
                                    <td class="row-value">${item.baseline_create_date}</td>
                                    <td class="row-value">${item.cust_payment_terms}</td>
                                    <td class="row-value">${item.invoice_id}</td>
                                    <td class="row-value">${item.aging_bucket}</td>
                                </tr>`
                tr.append(markup)
            })
        })
})

//to handel the cancel event
$('#advance_seach_cancel_btn').click(() => {
    $('#modaladvancesearch').toggle('none')
  })

/*********************************************** Getting DATA FROM SERVER ***********************************************/

function getData(page) {
    $('table tbody').empty()
    $.ajax({
        'url': 'http://localhost:8080/dummy/DataLoading',
        'type': 'GET',
        'data': { page: page },
        'datatype': 'json'
    }).then(data => {
        data = JSON.parse(data);
        data = data.invoice;
        console.log(data);
        var tr = $('table tbody')
        data.map(item => {
            markup = `<tr id="trow">
        				<td class='row-value'><input type="checkbox" name="checkbox" id="mark"
                        onclick="makeAvailable(this)"></td>
        						<td class="row-value">${item.sl_no}</td>
        						<td class="row-value">${item.business_code}</td>
        						<td class="row-value">${item.cust_number}</td>
        						<td class="row-value">${item.clear_date}</td>
        						<td class="row-value">${item.buisness_year}</td>
        						<td class="row-value">${item.doc_id}</td>
        						<td class="row-value">${item.posting_date}</td>
                                <td class="row-value">${item.document_create_date}</td>
        						<td class="row-value">${item.due_in_date}</td>
        						<td class="row-value">${item.invoice_currency}</td>
        						<td class="row-value">${item.document_type}</td>
        						<td class="row-value">${item.posting_id}</td>
        						<td class="row-value">${item.total_open_amount}</td>
                                <td class="row-value">${item.baseline_create_date}</td>
        						<td class="row-value">${item.cust_payment_terms}</td>
        						<td class="row-value">${item.invoice_id}</td>
        						<td class="row-value">${item.aging_bucket}</td>
        					</tr>`
            tr.append(markup)
        })
    })
}

$(document).ready(function() {
    getData(1)
})

$('#button-refresh').click(() => {
    $('table tbody').empty();
    getData(page);
})

/*********************************************** PAGINATION ***********************************************/

var page = 1;
var searchPage = 1;
var searchLength = 0;
$('#rightBtn').click(() => {
    if(searchLength == 0)
    {   page = page + 1;
        $('table tbody').empty();
        getData(page);
        console.log(page);
    }
    else
    {
        searchPage = searchPage + 1;
        $('table tbody').empty();
        input = document.getElementById("searchbox");
        filter = input.value.toUpperCase();
        searchCustomerId(filter)
    }
})

$('#leftBtn').click(() => {

    if(searchLength == 0)
    {
        console.log(page)
        if (page == 1) {
            alert('You are in the home page')
        }    
        else
        {
            page = page - 1;
            $('table tbody').empty();
            getData(page);
            console.log(page);
        }
    }
    else
    {
        if (searchPage == 1) 
        {
            alert('You are in the home page')
        }
        else
        {   
            searchPage = searchPage -1 ;
            $('table tbody').empty()
            input = document.getElementById("searchbox");
            filter = input.value.toUpperCase();
            searchCustomerId(filter)
        }
    }
})

/*********************************************** SEARCH ***********************************************/
function search()
{
    var input, filter, length;
    input = document.getElementById("searchbox");
    filter = input.value.toUpperCase();
    length = filter.length;
    searchLength = length;
    if(length > 0)
        searchCustomerId(filter);
    else
        getData(page);
}

function searchCustomerId(searchableId)
{
    $('table tbody').empty()
    $.ajax({
        'url': 'http://localhost:8080/dummy/SearchCustomerId',
        'type': 'GET',
        'data': { page: searchPage , customerId : searchableId},
        'datatype': 'json'
    }).then(data => {
        data = JSON.parse(data);
        data = data.invoice;
        console.log(data);
        var tr = $('table tbody')
        data.map(item => {
            markup = `<tr id="trow">
        				<td class='row-value'><input type="checkbox" name="checkbox" class="td_checkbox"
                        onclick="makeAvailable(this)"></td>
        						<td class="row-value">${item.sl_no}</td>
        						<td class="row-value">${item.business_code}</td>
        						<td class="row-value">${item.cust_number}</td>
        						<td class="row-value">${item.clear_date}</td>
        						<td class="row-value">${item.buisness_year}</td>
        						<td class="row-value">${item.doc_id}</td>
        						<td class="row-value">${item.posting_date}</td>
                                <td class="row-value">${item.document_create_date}</td>
        						<td class="row-value">${item.due_in_date}</td>
        						<td class="row-value">${item.invoice_currency}</td>
        						<td class="row-value">${item.document_type}</td>
        						<td class="row-value">${item.posting_id}</td>
        						<td class="row-value">${item.total_open_amount}</td>
                                <td class="row-value">${item.baseline_create_date}</td>
        						<td class="row-value">${item.cust_payment_terms}</td>
        						<td class="row-value">${item.invoice_id}</td>
        						<td class="row-value">${item.aging_bucket}</td>
        					</tr>`
            tr.append(markup)
        })
    })
}

$('#main_checkbox').click(function() {
    console.log("heh");
    $('input[type="checkbox"]').prop('checked', this.checked);
})


