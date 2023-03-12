const clientKey = 'test_ck_Lex6BJGQOVDWjekElxR3W4w2zNbg';
const customerKey = 'NAS9m6GNTavda1pXaifGE';
// 2. 결제위젯 초기화
const paymentWidget = PaymentWidget(clientKey, customerKey); // 회원 결제
const paymentWidget = PaymentWidget(clientKey, PaymentWidget.ANONYMOUS); // 비회원 결제

import { loadPaymentWidget, ANONYMOUS } from '@tosspayments/payment-widget-sdk';
const clientKey = 'test_ck_Lex6BJGQOVDWjekElxR3W4w2zNbg';
const customerKey = 'NAS9m6GNTavda1pXaifGE';

// async/await을 사용하는 경우
async function main() {
  const paymentWidget = await loadPaymentWidget(clientKey, customerKey); // 회원 결제
  const paymentWidget = await loadPaymentWidget(clientKey, ANONYMOUS); // 비회원 결제
  //...
}

// Promise를 사용하는 경우
loadPaymentWidget(clientKey, customerKey).then((paymentWidget) => {
  // ...
});
