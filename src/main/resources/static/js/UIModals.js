var UIModals = (function () {
    return {
        /** Tárolja, hogy van-e megjelenítve felugró ablak. */
        modalShown: false,

        showCopies: function (bookID) {
            if (!UIModals.modalShown) {
                $('#book_' + bookID + '_copies_modal').on('show.bs.modal', function () {
                    UIModals.modalShown = true;
                });
                $('#book_' + bookID + '_copies_modal').on('hidden.bs.modal', function () {
                    UIModals.modalShown = false;
                });
                $('#book_' + bookID + '_copies_modal').modal('show');
            }
        },

        showAddBookForm: function () {
            if (!UIModals.modalShown) {
                //$('#addBook_modalForm').validator(customValidations);
                //$('#addBook_modalForm').validator('update');
                $('#addBook_modalForm')[0].reset();

                $('#addBook_modal').on('show.bs.modal', function () {
                    UIModals.modalShown = true;
                });
                $('#addBook_modal').on('hidden.bs.modal', function () {
                    UIModals.modalShown = false;
                });

                /*$('.modal').on('shown.bs.modal', function () { // autofókusz az inputmezőre
                    $(this).find('[autofocus]').focus();
                });*/

                $('#addBook_modal').modal('show');
                //UIExecutions.alreadySubmitted = false;
            }
        },

        showModal: function (modal) {
            if (!UIModals.modalShown) {
                $('#insertModal').empty();
                $('#insertModal').html(modal);
                $('#modal').on('show.bs.modal', function () {
                    UIModals.modalShown = true;
                });
                $('#modal').on('hidden.bs.modal', function () {
                    UIModals.modalShown = false;
                });
                $('#modal').modal('show');
            }
        },
    };
})();