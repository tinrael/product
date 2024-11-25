import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  cloud: {
     projectID: "3725667",
     name: "script.js"
  },

  thresholds: {
    http_req_duration: ['p(95)<500'], // 95 percent of response times must be below 500ms
  },

  discardResponseBodies: true,

  scenarios: {
    constant_vus_scenario: {
      executor: 'constant-vus',
      vus: 10,
      duration: '30s',
    },
    ramping_vus_scenario: {
      executor: 'ramping-vus',
      startTime: '30s',
      startVUs: 0,
      stages: [
        { duration: '30s', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '30s', target: 0 },
      ],
    },
    constant_arrival_rate_scenario: {
      executor: 'constant-arrival-rate',
      startTime: '3m30s',
      exec: 'foo',
      duration: '5m',
      rate: 200,
      preAllocatedVUs: 2,
      maxVUs: 50,
    },
  },
};

export default function () {
  const response = http.get('http://localhost:8080/products/15');
  check(response, { 'status was 200': (r) => r.status == 200 });
  sleep(0.5); // 500ms
}

export function foo() {
  const response = http.get('http://localhost:8080/products/15');
  check(response, { 'status was 200': (r) => r.status == 200 });
}
