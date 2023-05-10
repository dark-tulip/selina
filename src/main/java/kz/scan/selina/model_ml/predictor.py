import pickle
import sys
import sklearn

inputContent = ' '.join(sys.argv[1:])


tf_idf_vec = pickle.load(open('Tf-idf-sql_injection.sav', 'rb'))
xgboost    = pickle.load(open('XGBoost_injection.sav', 'rb'))

input_sql = inputContent
input_sql_vec = tf_idf_vec.transform(input_sql)
sql_pred = xgboost.predict(input_sql_vec)  # 0 or 1

print(sql_pred)  # output result
