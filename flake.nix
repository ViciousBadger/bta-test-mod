{
  inputs = {
    nixpkgs.url = "nixpkgs/nixos-23.11";
  };
  outputs = {
    self,
    nixpkgs,
    flake-utils,
  }: let
    system = "x86_64-linux";
    pkgs = import nixpkgs {inherit system;};
    jdkVersion = pkgs.jdk17;
  in
    flake-utils.lib.eachDefaultSystem (system: {
      formatter = pkgs.alejandra;

      devShells.default = pkgs.mkShell rec {
        buildInputs = with pkgs; [
          jdkVersion
          libGL
          xorg.libXxf86vm
          xorg.xrandr
          libpulseaudio
          (jdt-language-server.override { jdk = jdkVersion; })
        ];
        LD_LIBRARY_PATH = "${nixpkgs.lib.makeLibraryPath buildInputs}";
      };
    });
}

