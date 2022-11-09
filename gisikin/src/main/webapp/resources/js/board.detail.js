document.getElementById('boardRemove').addEventListener('click', (e) => {
    document.getElementById('bno').value = document.getElementById('bnoVal').innerText;
    const delForm = document.getElementById('boardRmForm');
    delForm.setAttribute('action', '/board/remove');
    delForm.submit();
});

document.addEventListener('click', (e) => {
    if(e.target.classList.contains('img')) {
        document.getElementById('modalImg').src = e.target.src.replace("th_", "");
        document.getElementById('modalBtn').click();
    }
});