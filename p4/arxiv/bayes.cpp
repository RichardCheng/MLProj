#include <iostream>
#include <vector>
#include <cmath>
#include <sstream>
#include <fstream>
#include <map> 
#include <string>

using namespace std;

// A sample
typedef struct entry {
    int classification;
    vector<pair<int, int> > frequency;

    entry(string s) {
        stringstream ss(s);
        ss >> classification;
        int a;
        int b;
        char c;
        while (ss >> a >> c >> b) {
            frequency.push_back(make_pair(a, b));
        } 
    }

    entry() { }

    void print() {
        cout << "classification = " << classification << endl;
        for(int i = 0; i < frequency.size(); ++i) {
            cout << "Feature: " << frequency[i].first << " has value: " << frequency[i].second << endl;
        }
    }
} entry;

// Penalties for false pos and false neg
const double c10 = 10;
const double c00 = 0;
const double c01 = 1;
const double c11 = 0;

map<int, int> pos_word_map;
map<int, int> neg_word_map;
int pos_word_count = 0;
int neg_word_count = 0;
int max_feature = -1;
int pos_ins_count = 0;
int neg_ins_count = 0;

vector<double> pos_prob;
vector<double> neg_prob;
double py, pny;

int predictc(entry e) {
    double pos = log(py);
    double neg = log(pny);

    for (int i = 0; i <e.frequency.size(); ++i) {
        pos += pos_prob[e.frequency[i].first]*e.frequency[i].second;
        neg += neg_prob[e.frequency[i].first]*e.frequency[i].second;
    }

    if (log(c10-c11) + pos >= log(c01-c00) + neg) return 1;
    else return -1;
}

int predict(entry e) {
    double pos = log(py);
    double neg = log(pny);

    for (int i = 0; i <e.frequency.size(); ++i) {
        pos += pos_prob[e.frequency[i].first]*e.frequency[i].second;
        neg += neg_prob[e.frequency[i].first]*e.frequency[i].second;
    }

    if (pos > neg) return 1;
    else if (pos < neg) return -1;
    else return pos_ins_count >= neg_ins_count;
}

int main(int argc, char* argv[]) {
    if (argc != 3) {
        cout << "Usage: " << argv[0] << " <TRAINING_FILE> <VALIDATION_FILE>" << endl;
    } 
    
    ifstream train(argv[1]);
    ifstream valid(argv[2]);

    while (!train.eof() && train.good()) {
        string s;
        getline(train, s);
        if (s.length() > 0) {
            entry e(s);

            if (e.classification == 1) {
                ++pos_ins_count;
                for (int i = 0; i < e.frequency.size(); ++i) {
                    pos_word_map[e.frequency[i].first] += e.frequency[i].second;
                    pos_word_count += e.frequency[i].second;
                    if (e.frequency[i].first > max_feature) max_feature = e.frequency[i].first;
                }
            }
            else {
                ++neg_ins_count;
                for (int i = 0; i < e.frequency.size(); ++i) {
                    neg_word_map[e.frequency[i].first] += e.frequency[i].second;
                    neg_word_count += e.frequency[i].second;
                    if (e.frequency[i].first > max_feature) max_feature = e.frequency[i].first;
                }
            }
        }
    }

    cout << "MAX feature = " << max_feature << endl;

    // Calculate probability
    for (int i = 0; i <= max_feature; ++i) {
        pos_prob.push_back(log((double)(pos_word_map[i]+1)/(double)(pos_word_count + max_feature)));
        neg_prob.push_back(log((double)(neg_word_map[i]+1)/(double)(neg_word_count + max_feature)));
    }

    cout << "total pos = " << pos_word_count << endl;
    cout << "total neg = " << neg_word_count << endl;

    cout << "total pos count = " << pos_ins_count << endl;
    cout << "total neg count = " << neg_ins_count << endl;

    py = (double)(pos_ins_count) / ((double)(pos_ins_count+neg_ins_count));
    pny = (double)(neg_ins_count) / ((double)(pos_ins_count+neg_ins_count));

    cout << "py = " << py << endl;
    cout << "pny = " << pny << endl;

    // Do prediction
    int correct = 0;
    int falsepos = 0;
    int falseneg = 0;

    int correctc = 0;
    int falseposc = 0;
    int falsenegc = 0;

    while (!valid.eof() && valid.good()) {
        string s;
        getline(valid, s);
        if (s.length() > 0) {
            entry e(s);

            if (predict(e) == e.classification) {
                ++correct;            
            }
            else if (e.classification == 1) {
                ++falseneg;            
            }
            else {
                ++falsepos;
            }

            if (predictc(e) == e.classification) {
                ++correctc;            
            }
            else if (e.classification == 1) {
                ++falsenegc;            
            }
            else {
                ++falseposc;
            }
        }
    }

    cout << "correct = " << correct << endl;
    cout << "falsepos = " << falsepos << endl;
    cout << "falseneg = " << falseneg << endl;
    cout << "accuracy = " << (double)correct/((double)correct+falsepos+falseneg) << endl;

    cout << "part c:" << endl;
    cout << "correct = " << correctc << endl;
    cout << "falsepos = " << falseposc << endl;
    cout << "falseneg = " << falsenegc << endl;
    cout << "accuracy = " << (double)correctc/((double)correctc+falseposc+falsenegc) << endl;

    return 0;
}
