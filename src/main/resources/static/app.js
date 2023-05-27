$(document).ready(function() {
    $('.filter-item').click(function() {
        const value = $(this).attr('data-filter');
        if (value == 'all') {
            $('.post-box').show('1000')
            hideForms();
        } else if (value == 'comment') {
            $('.post-box').hide('1000');
            hideForms();
            $('#comment-div').show();
        } else if (value == 'complaints') {
            $('.post-box').hide('1000');
            hideForms();
            $('#complaint-div').show();
        } else {
            $('.post-box').not('.' + value).hide('1000')
            $('.post-box').filter('.' + value).show('1000')
            hideForms();
        }
    });

    // add active to btn
    $('.filter-item').click(function() {
        $(this).addClass("active-filter").siblings().removeClass("active-filter");
    });

    function hideForms() {
        $('#comment-div').hide();
        $('#complaint-div').hide();
    }
});

// header background change on scroll
let header = document.querySelector('header')

window.addEventListener('scroll', () => {
    header.classList.toggle('shadow', window.scrollY > 0)
})
