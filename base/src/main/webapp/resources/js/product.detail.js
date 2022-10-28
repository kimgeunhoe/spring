document.getElementById('productRemove').addEventListener('click', (e) => {
    // const pnVal = document.getElementById('pnVal').innerText;
    document.getElementById('pn').value = document.getElementById('pnoVal').innerText;
    const delForm = document.getElementById('productRmForm');
    delForm.setAttribute('action', '/product/remove');
    delForm.submit();
});

document.addEventListener('click', (e) => {
    if(e.target.classList.contains('img')) {
        document.getElementById('modalImg').src = e.target.src.replace("th_", "");
        document.getElementById('modalBtn').click();
    }
});