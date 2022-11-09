async function emailDupleCheckFromServer(emailVal) {
    try {
        const url = "/member/dupleCheck";
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify({email: emailVal})
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('dupleCheck').addEventListener('click', (e) => {
    e.preventDefault();
    let emailInputed = document.getElementById('email');
    let emailVal = emailInputed.value;

    if(emailVal=='') {
        alert('가입할 이메일을 입력해주세요');
        emailInputed.focus();
        return;
    } else {
        emailDupleCheckFromServer(emailVal).then(result => {
            console.log(typeof result, result);
            if(parseInt(result)) {
                alert('이미 사용 중인 이메일입니다');
                emailInputed.value = "";
                emailInputed.focus();
            } else {
                alert('사용 가능한 이메일입니다');
                document.getElementById("pwd").focus();
            }
        });
    }
});

const regExpPrevent = new RegExp("\.(exe|sh|bat|js|msi|dll)$"); //실행파일 막기
const regExpImage = new RegExp("\.(jpg|jpeg|png|gif|JPG|JPEG|PNG|GIF)$"); //이미지 파일만 허용
const maxSize = 2 * 1024 * 1024; //2mb

function fileSizeAndVaildation(fileName, fileSize) {
    if(!regExpImage.test(fileName)) {
        return 0;
    } else if(regExpPrevent.test(fileName)) {
        return 0;
    } else if(fileSize>maxSize) {
        return 0;
    } else {
        return 1;
    }
}

document.addEventListener('change', (e) => {
    if(e.target.id=='file') {
        document.getElementById('regBtn').disabled = false;
        //input type file element에 저장된 file 정보를 가져오는 property, 리턴은 객체형
        const fileObjects = document.getElementById('file').files;
        console.log(fileObjects[0]);
        const file = fileObjects[0];

        let div = document.getElementById('fileZone');
        div.innerHTML = '';
        let isOk = 1;
        let ul = '<ul class="list-group list-group-flush">';
        let vaildFile = fileSizeAndVaildation(file.name, file.size);
        isOk *= vaildFile;
        ul += `<li class="${vaildFile ? "bg-success text-white" : "bg-danger text-white"} list-group-item d-flex justify-content-between align-items-start">`;
        ul += `<div class="ms-2 me-auto">${file.name}</div>`;
        ul += `<span class="badge bg-primary rounded-pill">${file.size}</span></li>`;
        ul += '</ul>';

        div.innerHTML = ul;

        if(!isOk) {
            document.getElementById('regBtn').disabled = true;
        }
    }
});