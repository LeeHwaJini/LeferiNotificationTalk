
view.apply({
    validation: {
    },
    ajax: {
    }
});


$(document).ready(function () {
    $(".btn-close").click(function() {
        $(this).closest('.modal').hide();
    });

    $(".popup-close").click(function() {
        $(this).closest('.modal').hide();
    });


});
