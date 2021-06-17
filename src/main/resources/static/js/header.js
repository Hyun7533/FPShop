window.addEventListener('scroll', function (e) {
    var y = window.scrollY;
    var inner2 = document.querySelector('.inner2')

    if (y >= 130) {
        inner2.classList.add('sticky')
    } else {
        inner2.classList.remove('sticky')
    }
});


