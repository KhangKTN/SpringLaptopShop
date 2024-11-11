(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Fixed Navbar
    $(window).scroll(function () {
        if ($(window).width() < 992) {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow');
            } else {
                $('.fixed-top').removeClass('shadow');
            }
        } else {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow').css('top', -55);
            } else {
                $('.fixed-top').removeClass('shadow').css('top', 0);
            }
        } 
    });
    
    
   // Back to top button
   $(window).scroll(function () {
    if ($(this).scrollTop() > 300) {
        $('.back-to-top').fadeIn('slow');
    } else {
        $('.back-to-top').fadeOut('slow');
    }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Testimonial carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0:{
                items:1
            },
            576:{
                items:1
            },
            768:{
                items:1
            },
            992:{
                items:2
            },
            1200:{
                items:2
            }
        }
    });


    // vegetable carousel
    $(".vegetable-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0:{
                items:1
            },
            576:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:3
            },
            1200:{
                items:4
            }
        }
    });


    // Modal Video
    $(document).ready(function () {
        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })
    });


    // Product Quantity
    $('.quantity button').on('click', function () {
        let change = 0;

        var button = $(this);
        var oldValue = button.parent().parent().find('input').val();
        if (button.hasClass('btn-plus')) {
            var newVal = parseFloat(oldValue) + 1;
            change = 1;
        } else {
            if (oldValue > 1) {
                var newVal = parseFloat(oldValue) - 1;
                change = -1;
            } else {
                newVal = 1;
            }
        }
        const input = button.parent().parent().find('input');
        input.val(newVal);

        //set form index
        const index = input.attr("data-cart-detail-index")
        const el = document.getElementById(`cartDetails${index}.quantity`);
        $(el).val(newVal);

        //get price
        const price = input.attr("data-cart-detail-price");
        const id = input.attr("data-cart-detail-id");

        const priceElement = $(`p[data-cart-detail-id='${id}']`);
        if (priceElement) {
            const newPrice = +price * newVal;
            priceElement.text(formatCurrency(newPrice.toFixed(2)) + " đ");
        }

        //update total cart price
        const totalPriceElement = $(`p[data-cart-total-price]`);

        if (totalPriceElement && totalPriceElement.length) {
            const currentTotal = totalPriceElement.first().attr("data-cart-total-price");
            let newTotal = +currentTotal;
            if (change === 0) {
                newTotal = +currentTotal;
            } else {
                newTotal = change * (+price) + (+currentTotal);
            }

            //reset change
            change = 0;

            //update
            totalPriceElement?.each(function (index, element) {
                //update text
                $(totalPriceElement[index]).text(formatCurrency(newTotal.toFixed(2)) + " đ");

                //update data-attribute
                $(totalPriceElement[index]).attr("data-cart-total-price", newTotal);
            });
        }
    });

    // User to format currency VND
    function formatCurrency(value) {
        // Use the 'vi-VN' locale to format the number according to Vietnamese currency format
        // and 'VND' as the currency type for VietNam
        const formatter = new Intl.NumberFormat('vi-VN', {
            style: 'decimal',
            minimumFractionDigits: 0, // No decimal part for whole numbers
        });

        let formatted = formatter.format(value);
        // Replace dots with commas for thousands separator
        formatted = formatted.replace(/\./g, ',');
        return formatted;
    }


    // Handle search param from URL
    function setQueryParam() {
        // Get search param
        const listFilter = ['factory', 'target', 'price', 'cpu', 'vga', 'ram', 'storage', 'screen']
        const urlParams = new URLSearchParams(window.location.search);

        listFilter.forEach(filter => {
            const arr = urlParams.get(filter)?.split(',') || []
            if (arr.length > 0) {
                $(`#${filter}Filter input`).each(function () {
                    if (arr.includes($(this).val())) {
                        $(this).prop('checked', true)
                    }
                })
            } else {
                $(`#${filter}Filter input`).first().prop('checked', true)
            }
        });
        
        $(`#sortBy input[name="radio-sort"]`).val([sortBy])

        $('input[type=checkbox]').change(function () {
            const checkbox = $(this)
            if (checkbox.val() !== '') {
                $(this).parent().siblings('.form-check').first().find('input').prop('checked', false)
            }
        })

        // Set search param on URL when click filter
        $('#btnFilter').on('click', function () {
            const queryParam = []

            listFilter.forEach(filter => {
                const arr = []
                $(`#${filter}Filter input:checked`).each(function () {
                    const value = $(this).val().trim()
                    if (value.length !== 0) {
                        arr.push(value)
                    }
                })
                if (arr.length > 0) {
                    queryParam.push(`${filter}=${arr.join(',')}`)
                }
            })
            const sortValue = $('#sortBy input:checked').val()
            if (sortValue !== '') {
                queryParam.push(`sortBy=${sortValue}`)
            }

            let queryUrl = window.location.href.split('?')[0]
            if (queryParam.length > 0) {
                queryUrl += `?${queryParam.join('&')}`
            }
            window.location.replace(queryUrl)
        })
    }
    setQueryParam()

    // Button reset filter
    $('#btnClear').click(function () {
        window.location.replace(window.location.href.split('?')[0])
    })

})(jQuery);

