# !/bin/bash

source venv/bin/activate

pip install jupyterlab ipykernel

python3 -m ipykernel install --user --name venv --display-name "Python (venv)"

jupyter lab
