/*목록이 추가될 div 부모*/
const reviewContainer = $(".board-container-box");

/* 목록이 추가될 div에 화면에서 필요한 필드멤버 뿌려주기 */
function createDOM(reviews) {
    let text = ``;
    reviews.forEach((review, i) => {
        console.log(review)
        text += `
                <div class="review-box">
                <div>
                    <a class="review-modify" href="/store/41">수정</a>
                    <a class="review-delete" href="/store/41">삭제</a>
                </div>
                    <div class="review-list">
                        <div class="review-content-box" onclick="location.href='http://www.wishket.com'">
                            <a class="review-name-link">${review.boardTitle}</a>
                            <p class="pcontent">
                                <span class="review-date board-date">${review.boardRegisterDate}</span>
                            </p>
                            <p class="review-description-text">
                               ${review.boardContent}
                            </p>
                        </div>
                    </div>
                    <div class="board-container">
                        <img class="review-photo" src="/mypage/display?fileName=${review.filePath + '/' + review.fileUuid + '_' + review.fileOriginalName}" />
                    </div>
                    <div style="clear:both;"></div>
                </div>
                    `;


        // if(review.fileOriginalName != null){
        //     console.log("자 드가자~")
        //     text += `
        //         <div class="board-container">
        //             <img src="/mypage/display(fileName=${review.filePath} + '/' + ${review.fileUuid} + '_' + ${review.fileOriginalName})}" />
        //         </div>
        //         `;
        // }


        // text += `
        //         <div style="clear:both;"></div>
        //     `;
    })
    return text;
};
reviewContainer.append(createDOM(board));
console.log(board);