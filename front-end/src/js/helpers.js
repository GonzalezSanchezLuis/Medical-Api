export function token(url, options = {}) {
    const token = localStorage.getItem('token');
    if (!token) {
      throw new Error('No token found');
    }
  
    options.headers = {
      ...options.headers,
      'Authorization': 'Bearer ' + token,
      'Content-Type': 'application/json'
    };

    return fetch(url, options)
      .then(response => {
        if (!response.ok) {
          return response.text().then(text => { throw new Error(response.status + ": " + text); });
        }
        return response.json();
      });
  }
