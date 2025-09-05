import { useLocation, useParams, useSearchParams } from "react-router-dom"

function StudyParam() {
    // url?param1=value1 - queryString
    // url/param1/param2 - RestFul

    // 1. urlSearchParams (queryString only)
    // const [p, setParam] = useSearchParams()
    // console.log(p.get('num') + ' ' + p.get('name'))

    // 2. useParams (RestFul only)
    // const {num, name} = useParams()
    // console.log(num + ' ' + name)

    // 3. useParams().param
    // console.log(useParams().num + ' ' + useParams().name)

    // 4. state (need state attribute in Link tag, does not show up in URL)
    if (useLocation().state != null) {
        console.log(useLocation().state)
        console.log(useLocation().state.age + ' ' + useLocation().state.user)
    }

    // 3. useLocation (queryString only)
    // console.log(useLocation())
    const param = new URLSearchParams(useLocation().search)
    return (
        <>
            <h1>Study Param</h1>
            <div>{param.get('num')}</div>
            <div>{param.get('name')}</div>
        </>
    )
}

export default StudyParam