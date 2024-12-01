import numpy as np
from sklearn.datasets import load_boston
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error

boston = load_boston()
X=boston.data
Y=boston.target

X_train, X_test, Y_train, Y_test = train_test_split(X,Y,test_size=0.2,random_state=42)
model = LinearRegression()
model.fit(X_train,Y_train)

Y_pred = model.predict(X_test)

mse=mean_squared_error(Y_test,Y_pred)
print('Mean Squared Error:',mse)
