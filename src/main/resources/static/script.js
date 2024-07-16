document.addEventListener("DOMContentLoaded", function() {
    loadOrganisations();
});

function getCsrfToken() {
    const cookies = document.cookie.split(';');
    for (let cookie of cookies) {
        const [name, value] = cookie.trim().split('=');
        if (name === 'XSRF-TOKEN') {
            return decodeURIComponent(value);
        }
    }
    return null;
}

function registerUser() {
    const firstName = document.getElementById('first-name').value;
    const lastName = document.getElementById('last-name').value;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const email = document.getElementById('email').value;
    const telephone = document.getElementById('telephone').value;

    const user = {
        firstName: firstName,
        lastName: lastName,
        username: username,
        password: password,
        email: email,
        telephoneNumber: telephone
    };

    fetch('/api/users/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': getCsrfToken()
        },
        body: JSON.stringify(user)
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function loginUser() {
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;

    const credentials = {
        username: username,
        password: password
    };

    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': getCsrfToken()
        },
        body: JSON.stringify(credentials)
    })
        .then(response => response.json())
        .then(data => {
            alert('Login successful');
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function loadOrganisations() {
    fetch('/api/client-organisations')
        .then(response => response.json())
        .then(data => {
            const organisationList = document.getElementById('organisation-list');
            organisationList.innerHTML = '';
            data.forEach(organisation => {
                const div = document.createElement('div');
                div.className = 'organisation-item';
                div.innerHTML = `
                    <strong>${organisation.name}</strong> - 
                    Registration Date: ${organisation.registrationDate} - 
                    Expiry Date: ${organisation.expiryDate}
                    <button onclick="editOrganisation(${organisation.id})">Edit</button>
                    <button onclick="deleteOrganisation(${organisation.id})">Delete</button>
                    <button onclick="viewPersonnel(${organisation.id})">View Personnel</button>
                `;
                organisationList.appendChild(div);
            });
        });
}

function showCreateOrganisationForm() {
    document.getElementById('organisation-form').style.display = 'block';
    document.getElementById('form-title').innerText = 'Add New Organisation';
    document.getElementById('organisation-id').value = '';
    document.getElementById('organisation-name').value = '';
    document.getElementById('registration-date').value = '';
    document.getElementById('expiry-date').value = '';
}

function hideCreateOrganisationForm() {
    document.getElementById('organisation-form').style.display = 'none';
}

function saveOrganisation() {
    const id = document.getElementById('organisation-id').value;
    const name = document.getElementById('organisation-name').value;
    const registrationDate = document.getElementById('registration-date').value;
    const expiryDate = document.getElementById('expiry-date').value;

    const organisation = {
        name: name,
        registrationDate: registrationDate,
        expiryDate: expiryDate,
        enabled: true
    };

    const url = id ? `/api/client-organisations/${id}` : '/api/client-organisations';
    const method = id ? 'PUT' : 'POST';

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': getCsrfToken()
        },
        body: JSON.stringify(organisation)
    })
        .then(response => response.json())
        .then(data => {
            loadOrganisations();
            hideCreateOrganisationForm();
        });
}

function editOrganisation(id) {
    fetch(`/api/client-organisations/${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('organisation-form').style.display = 'block';
            document.getElementById('form-title').innerText = 'Edit Organisation';
            document.getElementById('organisation-id').value = data.id;
            document.getElementById('organisation-name').value = data.name;
            document.getElementById('registration-date').value = data.registrationDate;
            document.getElementById('expiry-date').value = data.expiryDate;
        });
}

function deleteOrganisation(id) {
    fetch(`/api/client-organisations/${id}`, {
        method: 'DELETE',
        headers: {
            'X-XSRF-TOKEN': getCsrfToken()
        }
    })
        .then(() => {
            loadOrganisations();
        });
}

function viewPersonnel(organisationId) {
    fetch(`/api/client-organisations/${organisationId}/personnel`)
        .then(response => response.json())
        .then(data => {
            const personnelList = document.getElementById('personnel-list');
            personnelList.innerHTML = '';
            data.forEach(personnel => {
                const div = document.createElement('div');
                div.className = 'personnel-item';
                div.innerHTML = `
                    <strong>${personnel.firstName} ${personnel.lastName}</strong> - 
                    Username: ${personnel.username} - 
                    Email: ${personnel.email} - 
                    Telephone: ${personnel.telephoneNumber}
                    <button onclick="editPersonnel(${personnel.id})">Edit</button>
                    <button onclick="deletePersonnel(${personnel.id})">Delete</button>
                `;
                personnelList.appendChild(div);
            });
            document.getElementById('personnel-section').style.display = 'block';
            document.getElementById('organisation-id-for-personnel').value = organisationId;
        });
}

function showCreatePersonnelForm() {
    document.getElementById('personnel-form').style.display = 'block';
    document.getElementById('personnel-form-title').innerText = 'Add New Personnel';
    document.getElementById('personnel-id').value = '';
    document.getElementById('personnel-first-name').value = '';
    document.getElementById('personnel-last-name').value = '';
    document.getElementById('personnel-username').value = '';
    document.getElementById('personnel-password').value = '';
    document.getElementById('personnel-email').value = '';
    document.getElementById('personnel-telephone').value = '';
}

function hideCreatePersonnelForm() {
    document.getElementById('personnel-form').style.display = 'none';
}

function savePersonnel() {
    const organisationId = document.getElementById('organisation-id-for-personnel').value;
    const id = document.getElementById('personnel-id').value;
    const firstName = document.getElementById('personnel-first-name').value;
    const lastName = document.getElementById('personnel-last-name').value;
    const username = document.getElementById('personnel-username').value;
    const password = document.getElementById('personnel-password').value;
    const email = document.getElementById('personnel-email').value;
    const telephoneNumber = document.getElementById('personnel-telephone').value;

    const personnel = {
        firstName: firstName,
        lastName: lastName,
        username: username,
        password: password,
        email: email,
        telephoneNumber: telephoneNumber,
        clientOrganisation: { id: organisationId }
    };

    const url = id ? `/api/personnel/${id}` : `/api/client-organisations/${organisationId}/personnel`;
    const method = id ? 'PUT' : 'POST';

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': getCsrfToken()
        },
        body: JSON.stringify(personnel)
    })
        .then(response => response.json())
        .then(data => {
            viewPersonnel(organisationId);
            hideCreatePersonnelForm();
        });
}

function editPersonnel(id) {
    fetch(`/api/personnel/${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('personnel-form').style.display = 'block';
            document.getElementById('personnel-form-title').innerText = 'Edit Personnel';
            document.getElementById('personnel-id').value = data.id;
            document.getElementById('personnel-first-name').value = data.firstName;
            document.getElementById('personnel-last-name').value = data.lastName;
            document.getElementById('personnel-username').value = data.username;
            document.getElementById('personnel-email').value = data.email;
            document.getElementById('personnel-telephone').value = data.telephoneNumber;
        });
}

function deletePersonnel(id) {
    const organisationId = document.getElementById('organisation-id-for-personnel').value;
    fetch(`/api/personnel/${id}`, {
        method: 'DELETE',
        headers: {
            'X-XSRF-TOKEN': getCsrfToken()
        }
    })
        .then(() => {
            viewPersonnel(organisationId);
        });
}
