import pickle

TF_IDF_VEC = pickle.load(open('model_ml/Tf-idf-sql_injection.sav', 'rb'))
XGBOOST = pickle.load(open('model_ml/XGBoost_injection.sav', 'rb'))


"""
returns 0 if False, 1 if True
"""
def predict_content(content: str) -> str:
    input_sql = [content]
    input_sql_vec = TF_IDF_VEC.transform(input_sql)

    result = XGBOOST.predict(input_sql_vec)  # numpy T array

    return str(int(result))
