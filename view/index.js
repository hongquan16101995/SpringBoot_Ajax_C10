let arr;

function findAll() {
    $.ajax({
        url: "http://localhost:8080/products",
        type: "GET",
        success(data) {
            let arr = data.content
            let context = `<table border="1"><tr>
                            <th>STT</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th colspan="2">Action</th>
                            </tr>`
            for (let i = 0; i < arr.length; i++) {
                context += `<tr>
                            <td>${i + 1}</td>
                            <td>${arr[i].name}</td>
                            <td><img src="${arr[i].imagePath}" alt="" width="100px" height="100px"></td>
                            <td>${arr[i].price}</td>
                            <td>${arr[i].category.name}</td>
                            <td><button onclick="updateForm(${arr[i].id})">Update</button></td>
                            <td><button onclick="deleteProduct(${arr[i].id})">Delete</button></td>
                            </tr>`
            }
            context += `</table>`
            document.getElementById("display").innerHTML = context
            $("#form").hide()
            $("#display").show()
        }
    })
}

function createForm() {
    let content = `<label><select id="category"></label>`
    for (let i = 0; i < arr.length; i++) {
        content += `<option value="${arr[i].id}">${arr[i].name}</option>`
    }
    content += `</select>`
    document.getElementById("cate").innerHTML = content
    $("#name").val("")
    $("#price").val("")
    $("#description").val("")
    $("#image").val("")
    document.getElementById("title").innerHTML = "Create form"
    $("#form").show()
    document.getElementById("action").setAttribute("onclick", "createProduct()")
    document.getElementById("action").innerHTML = "Create"
    $("#display").hide()
}

window.onload = getCategories

function getCategories() {
    $.ajax({
        url: "http://localhost:8080/products/categories",
        type: "GET",
        success(data) {
            arr = data.content
        }
    })
}

function createProduct() {
    let product = {
        name: $("#name").val(),
        price: $("#price").val(),
        quantity: $("#image").val(),
        category: {
            id: $("#category").val()
        }
    }
    $.ajax({
        url: "http://localhost:8080/products",
        type: "POST",
        contentType: "application/json",
        accept: "application/json",
        data: JSON.stringify(product),
        success() {
            findAll()
        }
    })
    event.preventDefault()
}

function updateForm(id) {
    $.ajax({
        url: `http://localhost:8080/products/${id}`,
        type: "GET",
        success(data) {
            $("#name").val(data.name)
            $("#price").val(data.price)
            $("#description").val(data.description)
            $("#image").val(data.imagePath)
            document.getElementById("title").innerHTML = "Update form"
            $("#form").show()
            document.getElementById("action").setAttribute("onclick", `updateProduct(${id})`)
            document.getElementById("action").innerHTML = "Update"
            $("#display").hide()
        }
    })
}

function updateProduct(id) {
    let product = {
        id: id,
        name: $("#name").val(),
        price: $("#price").val(),
        description: $("#description").val(),
        imagePath: $("#image").val(),
    }
    $.ajax({
        url: "http://localhost:8080/products",
        type: "POST",
        contentType: "application/json",
        accept: "application/json",
        data: JSON.stringify(product),
        success() {
            findAll()
        }
    })
    event.preventDefault()
}

function deleteProduct(id) {
    if (confirm("Có xóa không?")) {
        $.ajax({
            url: "http://localhost:8080/products/" + id,
            type: "DELETE",
            success() {
                findAll()
            }
        })
    }
}

function backHome() {
    $("#form").hide()
    $("#display").show()
    event.preventDefault()
}
