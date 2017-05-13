var UIHandlers = (function () {
    return {
        filterBookList: function () {
            UIHandlers.disableFilterBookList();
            var resultCount = 0;

            var regExpNotEmpty = new RegExp('\\S+');
            var filter_title_part = $('#filter_title_part').val().toLowerCase();
            var filter_author = $('#filter_author').val().toLowerCase();
            var filter_publisher = $('#filter_publisher').val().toLowerCase();
            var filter_year = $('#filter_year').val().toLowerCase();

            $("[id^=book_][id$=_row]").each(function (i) {
                const this_bookTitle = $(this).find('.bookTitle').html().toLowerCase();
                const this_bookSubtitle = $(this).find('.bookSubtitle').html().toLowerCase();
                const this_bookAuthor = $(this).find('.bookAuthor').html().toLowerCase();
                const this_bookPublisher = $(this).find('.bookPublisher').html().toLowerCase();
                const this_bookYear = $(this).find('.bookYear').html().toLowerCase();

                let match_filter_title_part = true;
                let match_filter_author = true;
                let match_filter_publisher = true;
                let match_filter_year = true;

                if (regExpNotEmpty.test(filter_title_part) &&
                    !((this_bookTitle.indexOf(filter_title_part) != -1
                        || this_bookSubtitle.indexOf(filter_title_part) != -1))) {
                    match_filter_title_part = false;
                }

                if (regExpNotEmpty.test(filter_author) && !(this_bookAuthor.indexOf(filter_author) != -1)) {
                    match_filter_author = false;
                }

                if (regExpNotEmpty.test(filter_publisher) && !(this_bookPublisher.indexOf(filter_publisher) != -1)) {
                    match_filter_publisher = false;
                }

                if (regExpNotEmpty.test(filter_year) && !(this_bookYear == filter_year)) {
                    match_filter_year = false;
                }

                if (!(match_filter_title_part && match_filter_author && match_filter_publisher && match_filter_year)) {
                    $(this).css('display', 'none');
                } else {
                    resultCount++;
                }
            });

            $('#resultCount').html(resultCount);
        },

        disableFilterBookList: function () {
            var resultCount = 0;

            $("[id^=book_][id$=_row]").each(function (i) {
                $(this).css('display', 'table-row');
                resultCount++;
            });

            $('#resultCount').html(resultCount);
        },
    };
})();