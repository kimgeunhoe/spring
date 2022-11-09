let bnoVal = document.getElementById('bnoVal').innerText;

async function postCommentToServer(cmtData) {
    try {
        const url = '/comment/post';
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('cmtSbmBtn').addEventListener('click', (e)=> {
    const cmtInputObj = document.getElementById('cmtText');
    if(cmtInputObj.value==null||cmtInputObj.value=='') {
        alert('댓글 내용을 입력해주세요');
        cmtInputObj.focus();
        return false;
    } else {
        let cmtData = {
            bno: bnoVal,
            writer: document.getElementById('cmtWriter').innerText,
            content: cmtInputObj.value
        };
        postCommentToServer(cmtData).then(result => {
            if(parseInt(result)) {
                alert('댓글 등록 성공');
                cmtInputObj.value = '';
                getCommentList(bnoVal);
            }
        });
    }
});

async function spreadCommentFromServer(bno, pageNo) {
    try {
        const resp = await fetch("/comment/"+bno+"/"+pageNo);
        const pagingHandler = await resp.json();
        return await pagingHandler;
    } catch (error) {
        console.log(error);
    }
}

function getCommentList(bno, pageNo=1) {
    spreadCommentFromServer(bno, pageNo).then(result => {
        console.log(result);
        document.getElementById('cmtQty').innerText = `A ${result.totalCount}개`;
        const sesWriter = document.getElementById('cmtWriter').innerText;
        if(result.cmtList.length && pageNo==1) {
            let tag = '<div id="cmtbody">';
            let saveDir = null;
            for (let i=0; i<result.cmtList.length; i++) {
                tag += '<hr><div><div>';
                if(result.pfList[i]!=null) {
                    saveDir = result.pfList[i].saveDir.replace("\\", "/");
                    tag += `<img src="/upload/${saveDir }/${result.pfList[i].uuid}_th_${result.pfList[i].fileName}">`;
                }
                tag += `${result.cmtList[i].writer }님 답변</div><div>${result.cmtList[i].content }</div><div>${result.cmtList[i].modAt }</div>`;
                if(sesWriter==result.cmtList[i].writer) {
                    tag += `<button type='button' class="btn btn-sm btn-warning cmtMod" data-cno='${result.cmtList[i].cno}'>수정</button>`;
                    tag += `<button type='button' class="btn btn-sm btn-danger cmtDel" data-cno='${result.cmtList[i].cno}'>삭제</button>`;
                }
                tag += '</div><hr>';
            }
            tag += '</div>';
            document.getElementById('cmtZone').innerHTML = tag;
        } else if(pageNo > 1) {
            const bodyTag = document.getElementById('cmtbody');
            let tag = '';
            let saveDir = null;
            for (let i=0; i<result.cmtList.length; i++) {
                tag += '<hr><div><div>';
                if(result.pfList[i]!=null) {
                    saveDir = result.pfList[i].saveDir.replace("\\", "/");
                    tag += `<img src="/upload/${saveDir }/${result.pfList[i].uuid}_th_${result.pfList[i].fileName}">`;
                }
                tag += `${result.cmtList[i].writer }님 답변</div><div>${result.cmtList[i].content }</div><div>${result.cmtList[i].modAt }</div>`;
                if(sesWriter==result.cmtList[i].writer) {
                    tag += `<button type='button' class="btn btn-sm btn-warning cmtMod" data-cno='${result.cmtList[i].cno}'>수정</button>`;
                    tag += `<button type='button' class="btn btn-sm btn-danger cmtDel" data-cno='${result.cmtList[i].cno}'>삭제</button>`;
                }
                tag += '</div><hr>';
            }
            tag += '</div>';
            bodyTag.innerHTML += tag;
        } else {
            document.getElementById('cmtZone').innerHTML = '<span>댓글을 달아주세요</span>';
        }
        const moreBtn = document.getElementById('moreBtn');
        if(pageNo < parseInt(Math.ceil(result.totalCount)/10.0)) {
            moreBtn.style.visibility = 'visible';
            let pageIdx = moreBtn.dataset.page;
            moreBtn.dataset.page = parseInt(pageIdx) + 1;
        } else {
            moreBtn.style.visibility = 'hidden';
        }
    });
}

async function commentUpdateToServer(cmtData) {
    try {
        const url = "/comment/"+cmtData.cno;
        const config = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function commentDeleteToServer(cnoVal) {
    try {
        const url = "/comment/"+cnoVal;
        const config = {
            method: 'DELETE'
        };
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click', (e) => {
    if(e.target.classList.contains('cmtMod')) {
        const cnoVal = e.target.dataset.cno;
        const div = e.target.closest('div');
        const contentVal = div.querySelector('div:nth-child(2)').innerText;

        document.querySelector('.modal-body').innerHTML = `<input type='text' class="form-control cmtModifuedText" value="${contentVal}">`;
        document.querySelector(".modSbmBtn").dataset.cno = cnoVal;
        document.getElementById('modalBtn').click();
    }
    if(e.target.classList.contains('modSbmBtn')) {
        const cmtModInput = document.querySelector('.cmtModifuedText');
        const cmtTextVal = cmtModInput.value;
        if(cmtTextVal=='') {
            alert('수정할 댓글 내용을 입력하세요');
            cmtModInput.focus();
            return false;
        } else {
            const cmtData = {
                cno: e.target.dataset.cno,
                content: cmtTextVal
            };
            commentUpdateToServer(cmtData).then(result => {
                if(parseInt(result)) {
                    document.querySelector('.btn-close').click();
                }
                getCommentList(bnoVal);
            });
        }
    }
    if(e.target.classList.contains('cmtDel')) {
        commentDeleteToServer(e.target.dataset.cno).then(result => {
            if(parseInt(result)) {
                alert('삭제 완료');
                getCommentList(bnoVal);
            }
        });
    }
    if(e.target.id=='moreBtn') {
        e.preventDefault();
        getCommentList(bnoVal, parseInt(e.target.dataset.page)+1);
    }
});