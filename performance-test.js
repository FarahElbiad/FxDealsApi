import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    // Configures 10 virtual users ramping up over 30 seconds
    vus: 10,
    duration: '30s',
};

export default function () {
    const url = 'http://localhost:9090/api/deals'; // our endpoint
    const payload = JSON.stringify({
        dealUniqueId: `DEAL_${Math.random().toString(36).substr(2, 9)}`,
        fromCurrencyIsoCode: 'USD',
        toCurrencyIsoCode: 'EUR',
        dealTimestamp: new Date().toISOString(),
        dealAmount: 1000.50
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(url, payload, params);

    // Requirement: Proper error/exception handling
    // We check if the status is 200 (Success) or 400 (Validation Failure/Duplicate)
    check(res, {
        'is status 200 or 400': (r) => r.status === 200 || r.status === 400,
        'transaction time < 500ms': (r) => r.timings.duration < 500,
    });

    sleep(1);
}