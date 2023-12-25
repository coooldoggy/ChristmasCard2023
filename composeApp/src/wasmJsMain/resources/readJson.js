let jsonData;

function getData(filePath) {
    return fetch(filePath)
        .then(response => response.json())
        .then(data => {
            jsonData = data;
            return JSON.stringify(jsonData);
        })
        .catch(error => {
            console.error('Error:', error);
            throw error;
        });
}