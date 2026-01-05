document.addEventListener('DOMContentLoaded', async function() {
    // fetch kthen Promise => fullfilled ose reject
    async function loadUsers(){
        const response = await fetch("/api/users");

        if (!response.ok) {
            throw Error("Error fetching users");
        }

        const users = await response.json();

        const tblUsers = document.getElementById("tblUsers");

        tblUsers.innerHTML = "";

        for (const user of users) {
            tblUsers.innerHTML += `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.role}</td>
                    <td>
                        <a href="#" class="btn btn-secondary">Update</a>
                        <button class="btn btn-danger ms-2">Delete</button>
                    </td>
                </tr>
            `;
        }
    }

    const btnUsers = document.getElementById("btnUsers");
    btnUsers.addEventListener("click", loadUsers)
});