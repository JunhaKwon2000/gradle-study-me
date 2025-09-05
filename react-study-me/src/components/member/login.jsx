export default function Login() {
    function login(e) {
        e.preventDefault()
        const form = new FormData(e.target); // form 빌드
        let all = Object.fromEntries(form.entries())
        // console.log(all) // all.username 등 꺼내서 검증 가능
        fetch('http://localhost:8080/api/member/login', {
            method: 'POST',
            body: form
        })
        // .then(response => response.json())
        .then(response => {
            const header = response.headers
            // console.log(header.get("Authorization"))
            localStorage.setItem("Authorization", header.get("Authorization"))
            // sessionStorage.setItem("Authorization", header.get("Authorization"))
        })
        .catch(e => console.log(e))
    }
    return (
        <>
            <h1>Login Page</h1>
            <form onSubmit={login}>
                <div>
                    <input type="text" name="username" />
                </div>
                <div>
                    <input type="password" name="password" />
                </div>
                <button>Login</button>
            </form>
        </>
    )
}