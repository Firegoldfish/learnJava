/*
输入：

一个整数数组 A 表示每笔交易的金额（正数为存入，负数为支出）。
一个字符串数组 D，每个字符串表示对应交易的日期（格式为 YYYY-MM-DD）。
规则：

账户初始余额为 0。
每个月会扣除 5 的账户管理费，除非：
该月至少有 3 笔刷卡支付（负数交易），且这些交易的总金额不少于 100。
我们需要计算 2020 年末的账户最终余额。
*/
#include <vector>
#include <string>
#include <unordered_map>
#include <sstream>
#include <iostream>

using namespace std;

// Helper function to get the "YYYY-MM" format from a date string "YYYY-MM-DD"
string getYearMonth(const string &date) {
    return date.substr(0, 7);  // Returns "YYYY-MM"
}

int solution(vector<int> &A, vector<string> &D) {
    unordered_map<string, int> monthlyCardTotal;
    unordered_map<string, int> monthlyCardCount;

    int balance = 0;

    // Process each transaction
    for (int i = 0; i < A.size(); ++i) {
        int amount = A[i];
        string month = getYearMonth(D[i]);

        // Update the balance with this transaction
        balance += amount;

        // If it’s a card payment (negative amount), update monthly totals and counts
        if (amount < 0) {
            monthlyCardTotal[month] += -amount;  // Add absolute value of card payment
            monthlyCardCount[month]++;
        }
    }

    // Apply monthly fees where applicable
    for (int month = 1; month <= 12; ++month) {
        // Construct "YYYY-MM" format for each month of the year 2020
        stringstream ss;
        ss << "2020-" << (month < 10 ? "0" : "") << month;
        string monthStr = ss.str();

        // Apply the fee if less than 3 card payments or total card payments < 100
        if (monthlyCardCount[monthStr] < 3 || monthlyCardTotal[monthStr] < 100) {
            balance -= 5;
        }
    }
    return balance;
}