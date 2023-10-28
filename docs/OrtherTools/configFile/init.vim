" 编码方式
set encoding=utf-8
" 显示行号
set nu
" 启用鼠标
set mouse=a
" 缩进大小
set tabstop=4
" 设置tab自动转为空格
set expandtab
" tab编辑等效的空格数
set shiftwidth=4
" >>缩进大小
set shiftwidth=4
" 语法高亮
syntax on 
" 启用256色
set t_Co=256
" 显示光标所在行
set cursorline
" 突出显示当前行
set cul
" 自动高亮匹配括号
set showmatch
" 总是显示状态栏
set laststatus=2
" 在状态栏显示光标位置
set ruler
" 不创建swap文件
set noswapfile

" 搜索配置
" 查找时忽略大小写
set ignorecase
" 搜索时，高亮匹配结果
set hlsearch
" 搜索时，每输入一个字符，就自动跳转到第一个匹配结果处
set incsearch

" 安装插件管理工具
if empty(glob('~/.config/nvim/autoload/plug.vim'))
    :exe '!curl -fLo ~/.config/nvim/autoload/plug.vim --create-dirs
                \ https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim'
    au VimEnter * PlugInstall --sync | source $MYVIMRC
endif

" 需要安装的插件（被上方插件管理）
call plug#begin('~/.config/nvim/plugged')
call plug#end()

