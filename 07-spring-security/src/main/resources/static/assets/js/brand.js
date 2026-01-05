document.addEventListener('DOMContentLoaded', async function () {

    const url = "/api/brands";
    let brands = [];

    async function loadBrands() {
        const response = await fetch(url);
        const data = await response.json();
        brands = data;

        let tbody = document.getElementById("brandTableBody");
        tbody.innerHTML = "";


        for (let brand of brands) {
            tbody.innerHTML += `<tr>
                <td>${brand.id}</td>
                <td>${brand.name}</td>
                <td>${brand.brandCode}</td>
                <td>${brand.logo}</td>
                <td>
                    <button class="btn btn-primary" data-id="${brand.id}">Edit</button>
                    <button class="btn btn-danger"  data-id="${brand.id}">Delete</button>
                </td>
            </tr>`
        }
    }



    await loadBrands();

    document.querySelector("#brandTableBody").addEventListener("click", async function (event) {
        console.log(event.target)
        if (event.target.tagName == "BUTTON" && event.target.textContent == "Delete") {
            await deleteBrand(event.target.dataset.id);
        }
    })

    async function deleteBrand(brandId) {
        const deleteUrl = url + "/" + brandId;
        fetch(deleteUrl, {
            method: "DELETE"
        })
            .then(response => {
                if (response.ok) {
                    loadBrands();
                }
            })
            .catch(error => {
                console.error(error);
            })
    }

});