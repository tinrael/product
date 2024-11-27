import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  cloud: {
     name: "script.js"
  },

  duration: '30s',
  vus: 20,

  thresholds: {
    http_req_duration: ['p(95)<500'], // 95 percent of response times must be below 500ms
  },

  discardResponseBodies: true,
};

export default function () {
  const response = http.get('https://test.k6.io/contacts.php');
  check(response, { 'status was 200': (r) => r.status == 200 });
  sleep(0.5); // 500ms
}
