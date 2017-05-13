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