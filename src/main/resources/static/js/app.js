window.onload = function() {
    const languageSelect = document.getElementById('languageSelect');
    languageSelect.addEventListener('change', (event) => {
        fetchStores(event.target.value);
    });
    fetchStores('영어');
}

function fetchStores(language) {
    fetch(`http://localhost:8080/index?language=`+ language)
        .then(response => {
           return  response.json()
        })
        .then(data => {
            console.log(data)
            const tbody = document.querySelector('#storeInfo tbody');
            tbody.innerHTML = '';
            var i=0;
            data.forEach(store => {
                i++;
                const tr = document.createElement('tr');
                const number = document.createElement('td');
                number.innerText = i;
                const tdName = document.createElement('td');
                tdName.innerText = store.store_name;
                const tdAddress = document.createElement('td');
                tdAddress.innerText = store.address;
                const tdForeignLanguage = document.createElement('td');
                tdForeignLanguage.innerText = store.language;
                tr.appendChild(number);
                tr.appendChild(tdName);
                tr.appendChild(tdAddress);
                tr.appendChild(tdForeignLanguage);
                tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Error:', error));
}
