function register() {
    const formulaire = document.getElementById('signin-form');
    formulaire.addEventListener('submit', (e) => {
        e.preventDefault();
        const input = document.querySelector('#email-signin');
        const email = input.value;
        const body = JSON.stringify({ email });
        fetch('/api/email', {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body
        })
            .then(r => r.json())
            .then(result => {
                console.log(result.ok);
                input.value = '';
                $('[data-toggle="popover"]').popover('show');
            })
            .catch(err => console.error(err));
    });
}

register();
