// get paramter as a js object
// export default function StudyProps(props) {...}

// get paramter directly as a js object
// export default function StudyProps({age, name})

// get paramter which is an object as a js object
// export default function StudyProps(props) {...}

// get paramter which is an object directly as a js object
export default function StudyProps({param}) {
    console.log(param.id + ' ' + param.pw)
    return (
        <>
            <h1>Props Page</h1>
        </>
    )
}